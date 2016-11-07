package tb.etu.ir331.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tb.etu.ir331.entities.Contrat;

/**
 *
 * @author sapk
 *
 */
@Stateless
@LocalBean
public class ContratDAO {

    /**
     * Référence vers le gestionnaire de persistance.
     */
    @PersistenceContext
    EntityManager entityManager;


    /**
     * Default constructor.
     */
    public ContratDAO() {
    }

    public Contrat findById(Integer id) throws Exception {
        System.out.println("ContratDAO.findById(" + id + ")");
        return entityManager.find(Contrat.class, (long) id);
    }

    public List<Contrat> findAll() {
        Query query = entityManager.createQuery("select contrat from Contrat contrat order by contrat.id");
        List l = query.getResultList();
        return (List<Contrat>) l;
    }

    public void validate(Contrat c) throws Exception {
        //TODO
    }

    public Contrat update(Contrat c) throws Exception {
        validate(c);
        Contrat result = entityManager.merge(c);
        return result;
    }

    public Contrat persist(Contrat c) throws Exception {
        validate(c);
        entityManager.persist(c); 
        return c;
    }

}
