
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Producto;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Proveedor;
import persistencia.exceptions.NonexistentEntityException;


public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public ProveedorJpaController(){
        emf = Persistence.createEntityManagerFactory("memorykings");
    }
    
    public void create(Proveedor proveedor) {
        if (proveedor.getListaProducto() == null) {
            proveedor.setListaProducto(new LinkedList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<Producto> attachedListaProducto = new LinkedList<Producto>();
            for (Producto listaProductoProductoToAttach : proveedor.getListaProducto()) {
                listaProductoProductoToAttach = em.getReference(listaProductoProductoToAttach.getClass(), listaProductoProductoToAttach.getIdProducto());
                attachedListaProducto.add(listaProductoProductoToAttach);
            }
            proveedor.setListaProducto(attachedListaProducto);
            em.persist(proveedor);
            for (Producto listaProductoProducto : proveedor.getListaProducto()) {
                Proveedor oldProveedorOfListaProductoProducto = listaProductoProducto.getProveedor();
                listaProductoProducto.setProveedor(proveedor);
                listaProductoProducto = em.merge(listaProductoProducto);
                if (oldProveedorOfListaProductoProducto != null) {
                    oldProveedorOfListaProductoProducto.getListaProducto().remove(listaProductoProducto);
                    oldProveedorOfListaProductoProducto = em.merge(oldProveedorOfListaProductoProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getIdProveedor());
            LinkedList<Producto> listaProductoOld = persistentProveedor.getListaProducto();
            LinkedList<Producto> listaProductoNew = proveedor.getListaProducto();
            LinkedList<Producto> attachedListaProductoNew = new LinkedList<Producto>();
            for (Producto listaProductoNewProductoToAttach : listaProductoNew) {
                listaProductoNewProductoToAttach = em.getReference(listaProductoNewProductoToAttach.getClass(), listaProductoNewProductoToAttach.getIdProducto());
                attachedListaProductoNew.add(listaProductoNewProductoToAttach);
            }
            listaProductoNew = attachedListaProductoNew;
            proveedor.setListaProducto(listaProductoNew);
            proveedor = em.merge(proveedor);
            for (Producto listaProductoOldProducto : listaProductoOld) {
                if (!listaProductoNew.contains(listaProductoOldProducto)) {
                    listaProductoOldProducto.setProveedor(null);
                    listaProductoOldProducto = em.merge(listaProductoOldProducto);
                }
            }
            for (Producto listaProductoNewProducto : listaProductoNew) {
                if (!listaProductoOld.contains(listaProductoNewProducto)) {
                    Proveedor oldProveedorOfListaProductoNewProducto = listaProductoNewProducto.getProveedor();
                    listaProductoNewProducto.setProveedor(proveedor);
                    listaProductoNewProducto = em.merge(listaProductoNewProducto);
                    if (oldProveedorOfListaProductoNewProducto != null && !oldProveedorOfListaProductoNewProducto.equals(proveedor)) {
                        oldProveedorOfListaProductoNewProducto.getListaProducto().remove(listaProductoNewProducto);
                        oldProveedorOfListaProductoNewProducto = em.merge(oldProveedorOfListaProductoNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = proveedor.getIdProveedor();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            LinkedList<Producto> listaProducto = proveedor.getListaProducto();
            for (Producto listaProductoProducto : listaProducto) {
                listaProductoProducto.setProveedor(null);
                listaProductoProducto = em.merge(listaProductoProducto);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
