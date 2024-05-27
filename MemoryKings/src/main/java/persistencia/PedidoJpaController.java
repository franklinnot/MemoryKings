
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Distribuidor;
import logica.DetallePedido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Pedido;
import persistencia.exceptions.NonexistentEntityException;


public class PedidoJpaController implements Serializable {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public PedidoJpaController(){
        emf = Persistence.createEntityManagerFactory("memorykings");
    }
    
    public void create(Pedido pedido) {
        if (pedido.getListaDetalle() == null) {
            pedido.setListaDetalle(new LinkedList<DetallePedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distribuidor distribuidor = pedido.getDistribuidor();
            if (distribuidor != null) {
                distribuidor = em.getReference(distribuidor.getClass(), distribuidor.getIdDistribuidor());
                pedido.setDistribuidor(distribuidor);
            }
            LinkedList<DetallePedido> attachedListaDetalle = new LinkedList<DetallePedido>();
            for (DetallePedido listaDetalleDetallePedidoToAttach : pedido.getListaDetalle()) {
                listaDetalleDetallePedidoToAttach = em.getReference(listaDetalleDetallePedidoToAttach.getClass(), listaDetalleDetallePedidoToAttach.getIdDetallePedido());
                attachedListaDetalle.add(listaDetalleDetallePedidoToAttach);
            }
            pedido.setListaDetalle(attachedListaDetalle);
            em.persist(pedido);
            if (distribuidor != null) {
                distribuidor.getListaPedido().add(pedido);
                distribuidor = em.merge(distribuidor);
            }
            for (DetallePedido listaDetalleDetallePedido : pedido.getListaDetalle()) {
                Pedido oldPedidoOfListaDetalleDetallePedido = listaDetalleDetallePedido.getPedido();
                listaDetalleDetallePedido.setPedido(pedido);
                listaDetalleDetallePedido = em.merge(listaDetalleDetallePedido);
                if (oldPedidoOfListaDetalleDetallePedido != null) {
                    oldPedidoOfListaDetalleDetallePedido.getListaDetalle().remove(listaDetalleDetallePedido);
                    oldPedidoOfListaDetalleDetallePedido = em.merge(oldPedidoOfListaDetalleDetallePedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getIdPedido());
            Distribuidor distribuidorOld = persistentPedido.getDistribuidor();
            Distribuidor distribuidorNew = pedido.getDistribuidor();
            LinkedList<DetallePedido> listaDetalleOld = persistentPedido.getListaDetalle();
            LinkedList<DetallePedido> listaDetalleNew = pedido.getListaDetalle();
            if (distribuidorNew != null) {
                distribuidorNew = em.getReference(distribuidorNew.getClass(), distribuidorNew.getIdDistribuidor());
                pedido.setDistribuidor(distribuidorNew);
            }
            LinkedList<DetallePedido> attachedListaDetalleNew = new LinkedList<DetallePedido>();
            for (DetallePedido listaDetalleNewDetallePedidoToAttach : listaDetalleNew) {
                listaDetalleNewDetallePedidoToAttach = em.getReference(listaDetalleNewDetallePedidoToAttach.getClass(), listaDetalleNewDetallePedidoToAttach.getIdDetallePedido());
                attachedListaDetalleNew.add(listaDetalleNewDetallePedidoToAttach);
            }
            listaDetalleNew = attachedListaDetalleNew;
            pedido.setListaDetalle(listaDetalleNew);
            pedido = em.merge(pedido);
            if (distribuidorOld != null && !distribuidorOld.equals(distribuidorNew)) {
                distribuidorOld.getListaPedido().remove(pedido);
                distribuidorOld = em.merge(distribuidorOld);
            }
            if (distribuidorNew != null && !distribuidorNew.equals(distribuidorOld)) {
                distribuidorNew.getListaPedido().add(pedido);
                distribuidorNew = em.merge(distribuidorNew);
            }
            for (DetallePedido listaDetalleOldDetallePedido : listaDetalleOld) {
                if (!listaDetalleNew.contains(listaDetalleOldDetallePedido)) {
                    listaDetalleOldDetallePedido.setPedido(null);
                    listaDetalleOldDetallePedido = em.merge(listaDetalleOldDetallePedido);
                }
            }
            for (DetallePedido listaDetalleNewDetallePedido : listaDetalleNew) {
                if (!listaDetalleOld.contains(listaDetalleNewDetallePedido)) {
                    Pedido oldPedidoOfListaDetalleNewDetallePedido = listaDetalleNewDetallePedido.getPedido();
                    listaDetalleNewDetallePedido.setPedido(pedido);
                    listaDetalleNewDetallePedido = em.merge(listaDetalleNewDetallePedido);
                    if (oldPedidoOfListaDetalleNewDetallePedido != null && !oldPedidoOfListaDetalleNewDetallePedido.equals(pedido)) {
                        oldPedidoOfListaDetalleNewDetallePedido.getListaDetalle().remove(listaDetalleNewDetallePedido);
                        oldPedidoOfListaDetalleNewDetallePedido = em.merge(oldPedidoOfListaDetalleNewDetallePedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pedido.getIdPedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getIdPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            Distribuidor distribuidor = pedido.getDistribuidor();
            if (distribuidor != null) {
                distribuidor.getListaPedido().remove(pedido);
                distribuidor = em.merge(distribuidor);
            }
            LinkedList<DetallePedido> listaDetalle = pedido.getListaDetalle();
            for (DetallePedido listaDetalleDetallePedido : listaDetalle) {
                listaDetalleDetallePedido.setPedido(null);
                listaDetalleDetallePedido = em.merge(listaDetalleDetallePedido);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pedido findPedido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
