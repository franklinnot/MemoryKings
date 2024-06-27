
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Pedido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Distribuidor;
import persistencia.exceptions.NonexistentEntityException;

public class DistribuidorJpaController implements Serializable {

    public DistribuidorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public DistribuidorJpaController(){
        emf = Persistence.createEntityManagerFactory("memorykings");
    }
    
    public void create(Distribuidor distribuidor) {
        if (distribuidor.getListaPedido() == null) {
            distribuidor.setListaPedido(new LinkedList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<Pedido> attachedListaPedido = new LinkedList<Pedido>();
            for (Pedido listaPedidoPedidoToAttach : distribuidor.getListaPedido()) {
                listaPedidoPedidoToAttach = em.getReference(listaPedidoPedidoToAttach.getClass(), listaPedidoPedidoToAttach.getIdPedido());
                attachedListaPedido.add(listaPedidoPedidoToAttach);
            }
            distribuidor.setListaPedido(attachedListaPedido);
            em.persist(distribuidor);
            for (Pedido listaPedidoPedido : distribuidor.getListaPedido()) {
                Distribuidor oldDistribuidorOfListaPedidoPedido = listaPedidoPedido.getDistribuidor();
                listaPedidoPedido.setDistribuidor(distribuidor);
                listaPedidoPedido = em.merge(listaPedidoPedido);
                if (oldDistribuidorOfListaPedidoPedido != null) {
                    oldDistribuidorOfListaPedidoPedido.getListaPedido().remove(listaPedidoPedido);
                    oldDistribuidorOfListaPedidoPedido = em.merge(oldDistribuidorOfListaPedidoPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Distribuidor distribuidor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distribuidor persistentDistribuidor = em.find(Distribuidor.class, distribuidor.getIdDistribuidor());
            LinkedList<Pedido> listaPedidoOld = persistentDistribuidor.getListaPedido();
            LinkedList<Pedido> listaPedidoNew = distribuidor.getListaPedido();
            LinkedList<Pedido> attachedListaPedidoNew = new LinkedList<Pedido>();
            for (Pedido listaPedidoNewPedidoToAttach : listaPedidoNew) {
                listaPedidoNewPedidoToAttach = em.getReference(listaPedidoNewPedidoToAttach.getClass(), listaPedidoNewPedidoToAttach.getIdPedido());
                attachedListaPedidoNew.add(listaPedidoNewPedidoToAttach);
            }
            listaPedidoNew = attachedListaPedidoNew;
            distribuidor.setListaPedido(listaPedidoNew);
            distribuidor = em.merge(distribuidor);
            for (Pedido listaPedidoOldPedido : listaPedidoOld) {
                if (!listaPedidoNew.contains(listaPedidoOldPedido)) {
                    listaPedidoOldPedido.setDistribuidor(null);
                    listaPedidoOldPedido = em.merge(listaPedidoOldPedido);
                }
            }
            for (Pedido listaPedidoNewPedido : listaPedidoNew) {
                if (!listaPedidoOld.contains(listaPedidoNewPedido)) {
                    Distribuidor oldDistribuidorOfListaPedidoNewPedido = listaPedidoNewPedido.getDistribuidor();
                    listaPedidoNewPedido.setDistribuidor(distribuidor);
                    listaPedidoNewPedido = em.merge(listaPedidoNewPedido);
                    if (oldDistribuidorOfListaPedidoNewPedido != null && !oldDistribuidorOfListaPedidoNewPedido.equals(distribuidor)) {
                        oldDistribuidorOfListaPedidoNewPedido.getListaPedido().remove(listaPedidoNewPedido);
                        oldDistribuidorOfListaPedidoNewPedido = em.merge(oldDistribuidorOfListaPedidoNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = distribuidor.getIdDistribuidor();
                if (findDistribuidor(id) == null) {
                    throw new NonexistentEntityException("The distribuidor with id " + id + " no longer exists.");
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
            Distribuidor distribuidor;
            try {
                distribuidor = em.getReference(Distribuidor.class, id);
                distribuidor.getIdDistribuidor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The distribuidor with id " + id + " no longer exists.", enfe);
            }
            LinkedList<Pedido> listaPedido = distribuidor.getListaPedido();
            for (Pedido listaPedidoPedido : listaPedido) {
                listaPedidoPedido.setDistribuidor(null);
                listaPedidoPedido = em.merge(listaPedidoPedido);
            }
            em.remove(distribuidor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Distribuidor> findDistribuidorEntities() {
        return findDistribuidorEntities(true, -1, -1);
    }

    public List<Distribuidor> findDistribuidorEntities(int maxResults, int firstResult) {
        return findDistribuidorEntities(false, maxResults, firstResult);
    }

    private List<Distribuidor> findDistribuidorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Distribuidor.class));
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

    public Distribuidor findDistribuidor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Distribuidor.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistribuidorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Distribuidor> rt = cq.from(Distribuidor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
