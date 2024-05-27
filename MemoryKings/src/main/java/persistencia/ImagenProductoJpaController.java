
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
import logica.ImagenProducto;
import logica.Producto;
import persistencia.exceptions.NonexistentEntityException;

public class ImagenProductoJpaController implements Serializable {

    public ImagenProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public ImagenProductoJpaController(){
        emf = Persistence.createEntityManagerFactory("memorykings");
    }
    
    public void create(ImagenProducto imagenProducto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = imagenProducto.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdProducto());
                imagenProducto.setProducto(producto);
            }
            em.persist(imagenProducto);
            if (producto != null) {
                producto.getListaImagen().add(imagenProducto);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ImagenProducto imagenProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ImagenProducto persistentImagenProducto = em.find(ImagenProducto.class, imagenProducto.getIdImagenProducto());
            Producto productoOld = persistentImagenProducto.getProducto();
            Producto productoNew = imagenProducto.getProducto();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdProducto());
                imagenProducto.setProducto(productoNew);
            }
            imagenProducto = em.merge(imagenProducto);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getListaImagen().remove(imagenProducto);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getListaImagen().add(imagenProducto);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = imagenProducto.getIdImagenProducto();
                if (findImagenProducto(id) == null) {
                    throw new NonexistentEntityException("The imagenProducto with id " + id + " no longer exists.");
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
            ImagenProducto imagenProducto;
            try {
                imagenProducto = em.getReference(ImagenProducto.class, id);
                imagenProducto.getIdImagenProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagenProducto with id " + id + " no longer exists.", enfe);
            }
            Producto producto = imagenProducto.getProducto();
            if (producto != null) {
                producto.getListaImagen().remove(imagenProducto);
                producto = em.merge(producto);
            }
            em.remove(imagenProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ImagenProducto> findImagenProductoEntities() {
        return findImagenProductoEntities(true, -1, -1);
    }

    public List<ImagenProducto> findImagenProductoEntities(int maxResults, int firstResult) {
        return findImagenProductoEntities(false, maxResults, firstResult);
    }

    private List<ImagenProducto> findImagenProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ImagenProducto.class));
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

    public ImagenProducto findImagenProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ImagenProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getImagenProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ImagenProducto> rt = cq.from(ImagenProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
