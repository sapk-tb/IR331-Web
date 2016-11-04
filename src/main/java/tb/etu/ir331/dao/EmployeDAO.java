package tb.etu.ir331.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tb.etu.ir331.entities.Employe;

/**
 *
 * @author sapk
 *
 */
@Stateless
@LocalBean
public class EmployeDAO {

    /**
     * Référence vers le gestionnaire de persistance.
     */
    @PersistenceContext
    EntityManager entityManager;

    List<Employe> EmployeList = new ArrayList<>(); //Mock of DB //TODO unMock

    /**
     * Default constructor.
     */
    public EmployeDAO() {
        // TODO Auto-generated constructor stub
    }

    public List<Employe> findByServiceId(Long Sid) throws Exception { //TODO unMock
        System.out.println("EmployeDAO.findByServiceId(" + Sid + ")");
        List<Employe> l = new ArrayList<>();
        for (Employe employe : EmployeList) {
            if(Objects.equals(employe.getId(), Sid)){
                l.add(employe);
            }
        }
        return l;
    }
    
    public Employe findById(Integer id) throws Exception {
        System.out.println("EmployeDAO.findById(" + id + ")");
        try {
            return EmployeList.get(id);//TODO unMock
        } catch (Exception e) {
            throw new Exception("Employe ID not Found, " + e.getMessage());
        }
        //return null; //entityManager.find(Service.class, id);
    }

    public List<Employe> findAll() {
        return EmployeList; //TODO UnMock
    }

    public Employe update(Employe e) {
        //entityManager.persist(s); //Use mock for a start
        EmployeList.set((int) (long) e.getId(), e); //TODO unMock
        return e;
    }

    public Employe persist(Employe e) {
        //entityManager.persist(s); //Use mock for a start
        e.setId(EmployeList.size()); //TODO unMock
        EmployeList.add(e);
        return e;
    }

}
