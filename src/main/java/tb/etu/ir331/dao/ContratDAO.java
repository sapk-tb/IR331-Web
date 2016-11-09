package tb.etu.ir331.dao;

import java.text.ParseException;
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

    public List<Contrat> findByEmployeId(Long eid) throws Exception {
        System.out.println("ContratDAO.findByEmployeId(" + eid + ")");
        Query query = entityManager.createQuery("select contrat from Contrat contrat where contrat.employe.id = :eid order by contrat.id");
        query.setParameter("eid", (long) eid);
        List l = query.getResultList();
        return (List<Contrat>) l;
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

    private void validate(Contrat c) throws Exception {
        //TODO Un employe en devrait avoir que un contrat actif ?
        //TODO check different type
        //TODO check different etat
        //type is null 
        if (c.getType() == null) {
            throw new Exception("Type is not defined");
        }
        //Employe is null 
        if (c.getEmploye() == null) {
            throw new Exception("Employe is not defined");
        }
        //Type CDD need end date
        if (c.getType().equals("CDD") && (c.getEndDate() == null || "".equals(c.getEndDate()))) {
            throw new Exception("Un contrat CDD necessite une date de fin.");
        }
        //Check format of date day/month/year
        try {
            Contrat.dateFormat.parse(c.getStartDate());
        } catch (ParseException e) {
            throw new Exception("StartDate est dans un format invalide.");
        }
        try {
            if (c.getEndDate() != null && !"".equals(c.getEndDate())) { //Enddate could be null
                Contrat.dateFormat.parse(c.getEndDate());
            }
        } catch (ParseException e) {
            throw new Exception("EndDate est dans un format invalide.");
        }

        //endate < startdate  
        if (c.getEndDate() != null && !"".equals(c.getEndDate())) { //Enddate could be null
            if (Contrat.dateFormat.parse(c.getStartDate()).after(Contrat.dateFormat.parse(c.getEndDate()))) {
                throw new Exception("StartDate est postérieur à EndDate.");
            }
        }
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
