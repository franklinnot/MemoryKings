
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.DetallePedido;
import logica.Pedido;
import logica.Producto;
import persistencia.exceptions.NonexistentEntityException;

public class DetallePedidoJpaController implements Serializable {

    public DetallePedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public DetallePedidoJpaController(){
        emf = Persistence.createEntityManagerFactory("memorykings");
    }
    
    public void create(DetallePedido detallePedido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = detallePedido.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdPedido());
                detallePedido.setPedido(pedido);
            }
            Producto producto = detallePedido.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdProducto());
                detallePedido.setProducto(producto);
            }
            em.persist(detallePedido);
            if (pedido != null) {
                pedido.getListaDetalle().add(detallePedido);
                pedido = em.merge(pedido);
            }
            if (producto != null) {
                producto.getListaDetalle().add(detallePedido);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetallePedido detallePedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetallePedido persistentDetallePedido = em.find(DetallePedido.class, detallePedido.getIdDetallePedido());
            Pedido pedidoOld = persistentDetallePedido.getPedido();
            Pedido pedidoNew = detallePedido.getPedido();
            Producto productoOld = persistentDetallePedido.getProducto();
            Producto productoNew = detallePedido.getProducto();
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdPedido());
                detallePedido.setPedido(pedidoNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdProducto());
                detallePedido.setProducto(productoNew);
            }
            detallePedido = em.merge(detallePedido);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getListaDetalle().remove(detallePedido);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getListaDetalle().add(detallePedido);
                pedidoNew = em.merge(pedidoNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getListaDetalle().remove(detallePedido);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getListaDetalle().add(detallePedido);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = detallePedido.getIdDetallePedido();
                if (findDetallePedido(id) == null) {
                    throw new NonexistentEntityException("The detallePedido with id " + id + " no longer exists.");
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
            DetallePedido detallePedido;
            try {
                detallePedido = em.getReference(DetallePedido.class, id);
                detallePedido.getIdDetallePedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallePedido with id " + id + " no longer exists.", enfe);
            }
            Pedido pedido = detallePedido.getPedido();
            if (pedido != null) {
                pedido.getListaDetalle().remove(detallePedido);
                pedido = em.merge(pedido);
            }
            Producto producto = detallePedido.getProducto();
            if (producto != null) {
                producto.getListaDetalle().remove(detallePedido);
                producto = em.merge(producto);
            }
            em.remove(detallePedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetallePedido> findDetallePedidoEntities() {
        return findDetallePedidoEntities(true, -1, -1);
    }

    public List<DetallePedido> findDetallePedidoEntities(int maxResults, int firstResult) {
        return findDetallePedidoEntities(false, maxResults, firstResult);
    }

    private List<DetallePedido> findDetallePedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetallePedido.class));
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

    public DetallePedido findDetallePedido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetallePedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallePedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetallePedido> rt = cq.from(DetallePedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
