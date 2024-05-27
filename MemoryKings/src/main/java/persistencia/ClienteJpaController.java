
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Consulta;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Cliente;
import persistencia.exceptions.NonexistentEntityException;

public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public ClienteJpaController(){
        emf = Persistence.createEntityManagerFactory("memorykings");
    }
    
    public void create(Cliente cliente) {
        if (cliente.getListaConsulta() == null) {
            cliente.setListaConsulta(new LinkedList<Consulta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<Consulta> attachedListaConsulta = new LinkedList<Consulta>();
            for (Consulta listaConsultaConsultaToAttach : cliente.getListaConsulta()) {
                listaConsultaConsultaToAttach = em.getReference(listaConsultaConsultaToAttach.getClass(), listaConsultaConsultaToAttach.getIdConsulta());
                attachedListaConsulta.add(listaConsultaConsultaToAttach);
            }
            cliente.setListaConsulta(attachedListaConsulta);
            em.persist(cliente);
            for (Consulta listaConsultaConsulta : cliente.getListaConsulta()) {
                Cliente oldClienteOfListaConsultaConsulta = listaConsultaConsulta.getCliente();
                listaConsultaConsulta.setCliente(cliente);
                listaConsultaConsulta = em.merge(listaConsultaConsulta);
                if (oldClienteOfListaConsultaConsulta != null) {
                    oldClienteOfListaConsultaConsulta.getListaConsulta().remove(listaConsultaConsulta);
                    oldClienteOfListaConsultaConsulta = em.merge(oldClienteOfListaConsultaConsulta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            LinkedList<Consulta> listaConsultaOld = persistentCliente.getListaConsulta();
            LinkedList<Consulta> listaConsultaNew = cliente.getListaConsulta();
            LinkedList<Consulta> attachedListaConsultaNew = new LinkedList<Consulta>();
            for (Consulta listaConsultaNewConsultaToAttach : listaConsultaNew) {
                listaConsultaNewConsultaToAttach = em.getReference(listaConsultaNewConsultaToAttach.getClass(), listaConsultaNewConsultaToAttach.getIdConsulta());
                attachedListaConsultaNew.add(listaConsultaNewConsultaToAttach);
            }
            listaConsultaNew = attachedListaConsultaNew;
            cliente.setListaConsulta(listaConsultaNew);
            cliente = em.merge(cliente);
            for (Consulta listaConsultaOldConsulta : listaConsultaOld) {
                if (!listaConsultaNew.contains(listaConsultaOldConsulta)) {
                    listaConsultaOldConsulta.setCliente(null);
                    listaConsultaOldConsulta = em.merge(listaConsultaOldConsulta);
                }
            }
            for (Consulta listaConsultaNewConsulta : listaConsultaNew) {
                if (!listaConsultaOld.contains(listaConsultaNewConsulta)) {
                    Cliente oldClienteOfListaConsultaNewConsulta = listaConsultaNewConsulta.getCliente();
                    listaConsultaNewConsulta.setCliente(cliente);
                    listaConsultaNewConsulta = em.merge(listaConsultaNewConsulta);
                    if (oldClienteOfListaConsultaNewConsulta != null && !oldClienteOfListaConsultaNewConsulta.equals(cliente)) {
                        oldClienteOfListaConsultaNewConsulta.getListaConsulta().remove(listaConsultaNewConsulta);
                        oldClienteOfListaConsultaNewConsulta = em.merge(oldClienteOfListaConsultaNewConsulta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            LinkedList<Consulta> listaConsulta = cliente.getListaConsulta();
            for (Consulta listaConsultaConsulta : listaConsulta) {
                listaConsultaConsulta.setCliente(null);
                listaConsultaConsulta = em.merge(listaConsultaConsulta);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
