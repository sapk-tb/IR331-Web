package tb.etu.ir331.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tb.etu.ir331.entities.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    List<Service> ServiceList = new ArrayList<>(); //Mock of DB //TODO unMock

    /**
     * Default constructor.
     */
    public ServiceDAO() {
        // TODO Auto-generated constructor stub
    }

    public Service findById(Integer id) throws Exception {
        System.out.println("ServiceDAO.findById(" + id + ")");

        try {
            return ServiceList.get(id);//TODO unMock
        } catch (Exception e) {
            throw new Exception("Service ID not Found, " + e.getMessage());
        }
        //return null; //entityManager.find(Service.class, id);
    }

    public List<Service> findAll() {
        return ServiceList; //TODO UnMock
    }

    public void validate(Service s) throws Exception {
        if (s.getNom() == null || "".equals(s.getNom().trim())) {
            throw new Exception("Service name is empty !");
        }
    }

    public Service update(Service s) throws Exception {
        validate(s);
        //entityManager.persist(s); //Use mock for a start
        ServiceList.set((int) (long) s.getId(), s); //TODO unMock
        return s;
    }

    public Service persist(Service s) throws Exception {
        validate(s);
        //entityManager.persist(s); //Use mock for a start
        s.setId(ServiceList.size()); //TODO unMock
        for (Service service : ServiceList) {
            if (service.getNom().equals(s.getNom())) {
                throw new Exception("Service with this name already exist !");
            }
        }
        ServiceList.add(s);
        return s;
    }

}
