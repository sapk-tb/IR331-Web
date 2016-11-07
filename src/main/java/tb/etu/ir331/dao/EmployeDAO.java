package tb.etu.ir331.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    //List<Employe> EmployeList = new ArrayList<>(); //Mock of DB //TODO unMock
    /**
     * Default constructor.
     */
    public EmployeDAO() {
        // TODO Auto-generated constructor stub
    }

    public List<Employe> findByServiceId(Long Sid) throws Exception { //TODO unMock
        System.out.println("EmployeDAO.findByServiceId(" + Sid + ")");
        List<Employe> l = new ArrayList<>();
        /* TODO
        for (Employe employe : EmployeList) {
            if (employe.getService() != null && Objects.equals(employe.getService().getId(), Sid)) {
                System.out.println("EmployeDAO.findByServiceId(" + Sid + ") found : " + employe.getId());
                l.add(employe);
            }
        }
         */
        return l;
    }

    public Employe findById(Integer id) throws Exception {
        System.out.println("EmployeDAO.findById(" + id + ")");
        /*
        try {
            return EmployeList.get(id);//TODO unMock
        } catch (Exception e) {
            throw new Exception("Employe ID not Found, " + e.getMessage());
        }
         */
        //return null; //
        return entityManager.find(Employe.class, id);
    }

    public List<Employe> findAll() {
        //return /EmployeList; //TODO UnMock
        Query query = entityManager.createQuery("select employe from Employe employe order by employe.id");
        List l = query.getResultList();

        return (List<Employe>) l;
    }

    public void validate(Employe e) throws Exception {
        if (e.getPrénom() == null || "".equals(e.getPrénom().trim())) {
            throw new Exception("Employe prenom is empty !");
        }
    }

    public Employe update(Employe e) throws Exception {
        validate(e);
        Employe result = entityManager.merge(e);
        //EmployeList.set((int) (long) e.getId(), e); //TODO unMock
        return result;
    }

    public Employe persist(Employe e) throws Exception {
        validate(e);
        entityManager.persist(e);
        //e.setId(EmployeList.size()); //TODO unMock
        //EmployeList.add(e);
        return e;
    }

}
