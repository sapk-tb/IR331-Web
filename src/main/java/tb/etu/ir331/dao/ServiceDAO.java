package tb.etu.ir331.dao;

import java.util.ArrayList;
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

    //List<Service> ServiceList = new ArrayList<>(); //Mock of DB //TODO unMock

    /**
     * Default constructor.
     */
    public ServiceDAO() {
        // TODO Auto-generated constructor stub
    }

    public Service findById(Integer id) throws Exception {
        System.out.println("ServiceDAO.findById(" + id + ")");
        /*
        try {
            return ServiceList.get(id);//TODO unMock
        } catch (Exception e) {
            throw new Exception("Service ID not Found, " + e.getMessage());
        }
        */
        //return null; //
        return entityManager.find(Service.class, id);
    }

    public List<Service> findAll() {
        //return ServiceList; //TODO UnMock
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
        //ServiceList.set((int) (long) s.getId(), s); //TODO unMock
        return result;
    }

    public Service persist(Service s) throws Exception {
        validate(s);
        entityManager.persist(s); 
        /*
        s.setId(ServiceList.size()); //TODO unMock
        for (Service service : ServiceList) {
            if (service.getNom().equals(s.getNom())) {
                throw new Exception("Service with this name already exist !");
            }
        }
        ServiceList.add(s); 
        */
        return s;
    }

}
