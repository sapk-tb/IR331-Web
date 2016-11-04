package tb.etu.ir331.dao;

import tb.etu.ir331.entities.Contrat;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sapk
 */
public class ContratDAO {

    /**
     * Référence vers le gestionnaire de persistance.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Default constructor.
     * 
     */
    public ContratDAO() {
        // TODO Auto-generated constructor stub
    }

    public Contrat findById(Integer id) {
        return entityManager.find(Contrat.class, id);
    }
}
