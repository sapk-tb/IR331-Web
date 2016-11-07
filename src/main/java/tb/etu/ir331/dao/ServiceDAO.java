package tb.etu.ir331.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tb.etu.ir331.entities.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sapk
 *
 */
@Stateless
@LocalBean
public class ServiceDAO {

    /**
     * Référence vers le gestionnaire de persistance.
     */
    @PersistenceContext
    EntityManager entityManager;


    /**
     * Default constructor.
     */
    public ServiceDAO() {
    }

    public Service findById(Integer id) throws Exception {
        System.out.println("ServiceDAO.findById(" + id + ")");
        return entityManager.find(Service.class, (long) id);
    }

    public List<Service> findByResp(int rid) throws Exception {
        System.out.println("ServiceDAO.findByResp(" + rid + ")");
        Query query = entityManager.createQuery("select service from Service service where service.responsable.id = :rid order by service.id");
        query.setParameter("rid", (long)rid);
        return (List<Service>) query.getResultList();
    }
    
    
    public List<Service> findByNom(String nom) throws Exception {
        System.out.println("ServiceDAO.findByNom(" + nom + ")");
        Query query = entityManager.createQuery("select service from Service service where service.nom = :nom order by service.id");
        query.setParameter("nom", nom);
        return (List<Service>) query.getResultList();
    }
    
    public List<Service> findAll() {
        Query query = entityManager.createQuery("select service from Service service order by service.id");
        List l = query.getResultList();
        return (List<Service>) l;
    }

    public void validate(Service s) throws Exception {
        if (s.getNom() == null || "".equals(s.getNom().trim())) {
            throw new Exception("Service name is empty !");
        }
    }

    public Service update(Service s) throws Exception {
        validate(s);
        Service result = entityManager.merge(s);
        return result;
    }

    public Service persist(Service s) throws Exception {
        validate(s);
        
        // Uniq name
        if(this.findByNom(s.getNom()).size()>0){
            throw new Exception("Service with this name already exist !");
        }
               
        entityManager.persist(s); 
        return s;
    }

}
