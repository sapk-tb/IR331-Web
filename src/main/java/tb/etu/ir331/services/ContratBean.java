package tb.etu.ir331.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tb.etu.ir331.dao.ContratDAO;
import tb.etu.ir331.dao.EmployeDAO;
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
    @EJB
    private EmployeDAO employeDAO;


    @Override
    public void create(int empId, String type) throws Exception {
        Date today = Calendar.getInstance().getTime();
        this.create(empId, type, "waitsign", Contrat.dateFormat.format(today), null);
    }

    @Override
    public void create(int empId, String type, String etat, String startDate, String endDate) throws Exception {
        Employe emp = employeDAO.findById(empId);
 
        Contrat c = new Contrat();

        c.setEmploye(emp);
        c.setType(type);
        c.setEtat(etat);
        c.setStartDate(startDate);
        c.setEndDate(endDate);

        contratDAO.persist(c);
    }

    @Override
    public void sign(int id) throws Exception {
        Contrat c = this.getContrat(id);

        if (!c.getEtat().equals("waitsign")) {
            throw new Exception("Le contrat n'est pas en attente de signature.");
        }
        c.setEtat("active");
//        c.setDate();

        contratDAO.update(c);
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
