
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
import logica.Categoria;
import persistencia.exceptions.NonexistentEntityException;


public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public CategoriaJpaController(){
        emf = Persistence.createEntityManagerFactory("memorykings");
    }
    
    public void create(Categoria categoria) {
        if (categoria.getListaProducto() == null) {
            categoria.setListaProducto(new LinkedList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<Producto> attachedListaProducto = new LinkedList<Producto>();
            for (Producto listaProductoProductoToAttach : categoria.getListaProducto()) {
                listaProductoProductoToAttach = em.getReference(listaProductoProductoToAttach.getClass(), listaProductoProductoToAttach.getIdProducto());
                attachedListaProducto.add(listaProductoProductoToAttach);
            }
            categoria.setListaProducto(attachedListaProducto);
            em.persist(categoria);
            for (Producto listaProductoProducto : categoria.getListaProducto()) {
                Categoria oldCategoriaOfListaProductoProducto = listaProductoProducto.getCategoria();
                listaProductoProducto.setCategoria(categoria);
                listaProductoProducto = em.merge(listaProductoProducto);
                if (oldCategoriaOfListaProductoProducto != null) {
                    oldCategoriaOfListaProductoProducto.getListaProducto().remove(listaProductoProducto);
                    oldCategoriaOfListaProductoProducto = em.merge(oldCategoriaOfListaProductoProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getIdCategoria());
            LinkedList<Producto> listaProductoOld = persistentCategoria.getListaProducto();
            LinkedList<Producto> listaProductoNew = categoria.getListaProducto();
            LinkedList<Producto> attachedListaProductoNew = new LinkedList<Producto>();
            for (Producto listaProductoNewProductoToAttach : listaProductoNew) {
                listaProductoNewProductoToAttach = em.getReference(listaProductoNewProductoToAttach.getClass(), listaProductoNewProductoToAttach.getIdProducto());
                attachedListaProductoNew.add(listaProductoNewProductoToAttach);
            }
            listaProductoNew = attachedListaProductoNew;
            categoria.setListaProducto(listaProductoNew);
            categoria = em.merge(categoria);
            for (Producto listaProductoOldProducto : listaProductoOld) {
                if (!listaProductoNew.contains(listaProductoOldProducto)) {
                    listaProductoOldProducto.setCategoria(null);
                    listaProductoOldProducto = em.merge(listaProductoOldProducto);
                }
            }
            for (Producto listaProductoNewProducto : listaProductoNew) {
                if (!listaProductoOld.contains(listaProductoNewProducto)) {
                    Categoria oldCategoriaOfListaProductoNewProducto = listaProductoNewProducto.getCategoria();
                    listaProductoNewProducto.setCategoria(categoria);
                    listaProductoNewProducto = em.merge(listaProductoNewProducto);
                    if (oldCategoriaOfListaProductoNewProducto != null && !oldCategoriaOfListaProductoNewProducto.equals(categoria)) {
                        oldCategoriaOfListaProductoNewProducto.getListaProducto().remove(listaProductoNewProducto);
                        oldCategoriaOfListaProductoNewProducto = em.merge(oldCategoriaOfListaProductoNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = categoria.getIdCategoria();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getIdCategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            LinkedList<Producto> listaProducto = categoria.getListaProducto();
            for (Producto listaProductoProducto : listaProducto) {
                listaProductoProducto.setCategoria(null);
                listaProductoProducto = em.merge(listaProductoProducto);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
