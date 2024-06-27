
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Categoria;
import logica.Proveedor;
import logica.DetallePedido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.ImagenProducto;
import logica.Producto;
import persistencia.exceptions.NonexistentEntityException;


public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //
    public ProductoJpaController(){
        emf = Persistence.createEntityManagerFactory("memorykings");
    }

    public void create(Producto producto) {
        if (producto.getListaDetalle() == null) {
            producto.setListaDetalle(new LinkedList<DetallePedido>());
        }
        if (producto.getListaImagen() == null) {
            producto.setListaImagen(new LinkedList<ImagenProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoria = producto.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getIdCategoria());
                producto.setCategoria(categoria);
            }
            Proveedor proveedor = producto.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdProveedor());
                producto.setProveedor(proveedor);
            }
            LinkedList<DetallePedido> attachedListaDetalle = new LinkedList<DetallePedido>();
            for (DetallePedido listaDetalleDetallePedidoToAttach : producto.getListaDetalle()) {
                listaDetalleDetallePedidoToAttach = em.getReference(listaDetalleDetallePedidoToAttach.getClass(), listaDetalleDetallePedidoToAttach.getIdDetallePedido());
                attachedListaDetalle.add(listaDetalleDetallePedidoToAttach);
            }
            producto.setListaDetalle(attachedListaDetalle);
            LinkedList<ImagenProducto> attachedListaImagen = new LinkedList<ImagenProducto>();
            for (ImagenProducto listaImagenImagenProductoToAttach : producto.getListaImagen()) {
                listaImagenImagenProductoToAttach = em.getReference(listaImagenImagenProductoToAttach.getClass(), listaImagenImagenProductoToAttach.getIdImagenProducto());
                attachedListaImagen.add(listaImagenImagenProductoToAttach);
            }
            producto.setListaImagen(attachedListaImagen);
            em.persist(producto);
            if (categoria != null) {
                categoria.getListaProducto().add(producto);
                categoria = em.merge(categoria);
            }
            if (proveedor != null) {
                proveedor.getListaProducto().add(producto);
                proveedor = em.merge(proveedor);
            }
            for (DetallePedido listaDetalleDetallePedido : producto.getListaDetalle()) {
                Producto oldProductoOfListaDetalleDetallePedido = listaDetalleDetallePedido.getProducto();
                listaDetalleDetallePedido.setProducto(producto);
                listaDetalleDetallePedido = em.merge(listaDetalleDetallePedido);
                if (oldProductoOfListaDetalleDetallePedido != null) {
                    oldProductoOfListaDetalleDetallePedido.getListaDetalle().remove(listaDetalleDetallePedido);
                    oldProductoOfListaDetalleDetallePedido = em.merge(oldProductoOfListaDetalleDetallePedido);
                }
            }
            for (ImagenProducto listaImagenImagenProducto : producto.getListaImagen()) {
                Producto oldProductoOfListaImagenImagenProducto = listaImagenImagenProducto.getProducto();
                listaImagenImagenProducto.setProducto(producto);
                listaImagenImagenProducto = em.merge(listaImagenImagenProducto);
                if (oldProductoOfListaImagenImagenProducto != null) {
                    oldProductoOfListaImagenImagenProducto.getListaImagen().remove(listaImagenImagenProducto);
                    oldProductoOfListaImagenImagenProducto = em.merge(oldProductoOfListaImagenImagenProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Categoria categoriaOld = persistentProducto.getCategoria();
            Categoria categoriaNew = producto.getCategoria();
            Proveedor proveedorOld = persistentProducto.getProveedor();
            Proveedor proveedorNew = producto.getProveedor();
            LinkedList<DetallePedido> listaDetalleOld = persistentProducto.getListaDetalle();
            LinkedList<DetallePedido> listaDetalleNew = producto.getListaDetalle();
            LinkedList<ImagenProducto> listaImagenOld = persistentProducto.getListaImagen();
            LinkedList<ImagenProducto> listaImagenNew = producto.getListaImagen();
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getIdCategoria());
                producto.setCategoria(categoriaNew);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdProveedor());
                producto.setProveedor(proveedorNew);
            }
            LinkedList<DetallePedido> attachedListaDetalleNew = new LinkedList<DetallePedido>();
            for (DetallePedido listaDetalleNewDetallePedidoToAttach : listaDetalleNew) {
                listaDetalleNewDetallePedidoToAttach = em.getReference(listaDetalleNewDetallePedidoToAttach.getClass(), listaDetalleNewDetallePedidoToAttach.getIdDetallePedido());
                attachedListaDetalleNew.add(listaDetalleNewDetallePedidoToAttach);
            }
            listaDetalleNew = attachedListaDetalleNew;
            producto.setListaDetalle(listaDetalleNew);
            LinkedList<ImagenProducto> attachedListaImagenNew = new LinkedList<ImagenProducto>();
            for (ImagenProducto listaImagenNewImagenProductoToAttach : listaImagenNew) {
                listaImagenNewImagenProductoToAttach = em.getReference(listaImagenNewImagenProductoToAttach.getClass(), listaImagenNewImagenProductoToAttach.getIdImagenProducto());
                attachedListaImagenNew.add(listaImagenNewImagenProductoToAttach);
            }
            listaImagenNew = attachedListaImagenNew;
            producto.setListaImagen(listaImagenNew);
            producto = em.merge(producto);
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getListaProducto().remove(producto);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getListaProducto().add(producto);
                categoriaNew = em.merge(categoriaNew);
            }
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getListaProducto().remove(producto);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getListaProducto().add(producto);
                proveedorNew = em.merge(proveedorNew);
            }
            for (DetallePedido listaDetalleOldDetallePedido : listaDetalleOld) {
                if (!listaDetalleNew.contains(listaDetalleOldDetallePedido)) {
                    listaDetalleOldDetallePedido.setProducto(null);
                    listaDetalleOldDetallePedido = em.merge(listaDetalleOldDetallePedido);
                }
            }
            for (DetallePedido listaDetalleNewDetallePedido : listaDetalleNew) {
                if (!listaDetalleOld.contains(listaDetalleNewDetallePedido)) {
                    Producto oldProductoOfListaDetalleNewDetallePedido = listaDetalleNewDetallePedido.getProducto();
                    listaDetalleNewDetallePedido.setProducto(producto);
                    listaDetalleNewDetallePedido = em.merge(listaDetalleNewDetallePedido);
                    if (oldProductoOfListaDetalleNewDetallePedido != null && !oldProductoOfListaDetalleNewDetallePedido.equals(producto)) {
                        oldProductoOfListaDetalleNewDetallePedido.getListaDetalle().remove(listaDetalleNewDetallePedido);
                        oldProductoOfListaDetalleNewDetallePedido = em.merge(oldProductoOfListaDetalleNewDetallePedido);
                    }
                }
            }
            for (ImagenProducto listaImagenOldImagenProducto : listaImagenOld) {
                if (!listaImagenNew.contains(listaImagenOldImagenProducto)) {
                    listaImagenOldImagenProducto.setProducto(null);
                    listaImagenOldImagenProducto = em.merge(listaImagenOldImagenProducto);
                }
            }
            for (ImagenProducto listaImagenNewImagenProducto : listaImagenNew) {
                if (!listaImagenOld.contains(listaImagenNewImagenProducto)) {
                    Producto oldProductoOfListaImagenNewImagenProducto = listaImagenNewImagenProducto.getProducto();
                    listaImagenNewImagenProducto.setProducto(producto);
                    listaImagenNewImagenProducto = em.merge(listaImagenNewImagenProducto);
                    if (oldProductoOfListaImagenNewImagenProducto != null && !oldProductoOfListaImagenNewImagenProducto.equals(producto)) {
                        oldProductoOfListaImagenNewImagenProducto.getListaImagen().remove(listaImagenNewImagenProducto);
                        oldProductoOfListaImagenNewImagenProducto = em.merge(oldProductoOfListaImagenNewImagenProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            Categoria categoria = producto.getCategoria();
            if (categoria != null) {
                categoria.getListaProducto().remove(producto);
                categoria = em.merge(categoria);
            }
            Proveedor proveedor = producto.getProveedor();
            if (proveedor != null) {
                proveedor.getListaProducto().remove(producto);
                proveedor = em.merge(proveedor);
            }
            LinkedList<DetallePedido> listaDetalle = producto.getListaDetalle();
            for (DetallePedido listaDetalleDetallePedido : listaDetalle) {
                listaDetalleDetallePedido.setProducto(null);
                listaDetalleDetallePedido = em.merge(listaDetalleDetallePedido);
            }
            LinkedList<ImagenProducto> listaImagen = producto.getListaImagen();
            for (ImagenProducto listaImagenImagenProducto : listaImagen) {
                listaImagenImagenProducto.setProducto(null);
                listaImagenImagenProducto = em.merge(listaImagenImagenProducto);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
