package tb.etu.ir331.dao;

import java.util.List;
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

    /**
     * Default constructor.
     */
    public EmployeDAO() {
    }

    public List<Employe> findByServiceId(Long sid) throws Exception {
        System.out.println("EmployeDAO.findByServiceId(" + sid + ")");
        //Query query = entityManager.createQuery("select employe from Employe employe where service.id == "+Sid+" order by employe.id");
        Query query = entityManager.createQuery("select employe from Employe employe where employe.service.id = :sid order by employe.id");
      	query.setParameter("sid", (long) sid);
        List l = query.getResultList();
        return (List<Employe>) l;
    }

    public Employe findById(Integer id) throws Exception {
        System.out.println("EmployeDAO.findById(" + id + ")");
        return entityManager.find(Employe.class, (long) id);
    }

    public List<Employe> findAll() {
        Query query = entityManager.createQuery("select employe from Employe employe order by employe.id");
        List l = query.getResultList();
        return (List<Employe>) l;
    }

    private void validate(Employe e) throws Exception {
        if (e.getPrenom() == null || "".equals(e.getPrenom().trim())) {
            throw new Exception("Employe prenom is empty !");
        }
    }

    public Employe update(Employe e) throws Exception {
        validate(e);
        Employe result = entityManager.merge(e);
        return result;
    }

    public Employe persist(Employe e) throws Exception {
        validate(e);
        entityManager.persist(e);
        return e;
    }

}
