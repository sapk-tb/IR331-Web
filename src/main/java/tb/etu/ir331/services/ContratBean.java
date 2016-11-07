package tb.etu.ir331.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tb.etu.ir331.dao.ContratDAO;
import tb.etu.ir331.entities.Contrat;
import tb.etu.ir331.entities.Employe;

/**
 *
 * @author sapk
 */
@Stateless
@LocalBean
public class ContratBean implements IContratBean {

    @EJB
    private ContratDAO contratDAO;

    @Override
    public void create(Employe emp, String type) throws Exception {
        this.create(emp, type, "waitsign");
    }
    @Override
    public void create(Employe emp, String type, String etat) throws Exception {
        Contrat c = new Contrat();

        c.setEmploye(emp);
        c.setType(type);
        c.setEtat(etat);
//        c.setDate();

        contratDAO.persist(c);
    }

    @Override
    public Contrat getContrat(int id) throws Exception {
        return contratDAO.findById(id);
    }

    @Override
    public List<Contrat> list() {
        return contratDAO.findAll();
    }
}
