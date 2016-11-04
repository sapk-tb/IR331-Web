package tb.etu.ir331.services;

import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tb.etu.ir331.dao.EmployeDAO;
import tb.etu.ir331.dao.ServiceDAO;
import tb.etu.ir331.entities.Employe;
import tb.etu.ir331.entities.Service;

/**
 *
 * @author sapk
 */
@Stateless
@LocalBean
public class EmployeBean implements IEmployeBean {

    //SessionContext ctx;
    @EJB
    private EmployeDAO employeDAO;
    @EJB
    private ServiceDAO serviceDAO;

    @Override
    public void create(String prenom, String nom) {
        this.create(prenom, nom, null);
    }

    @Override
    public void create(String prenom, String nom, Service service) {
        Employe e = new Employe();
        e.setPrénom(prenom);
        e.setNom(nom);
        e.setService(service);
        employeDAO.persist(e);
    }

    @Override
    public Employe getEmploye(int id) throws Exception {
        return employeDAO.findById(id);
    }

    @Override
    public List<Employe> list() {
        return employeDAO.findAll();
    }

    @Override
    public void attach(int idEmp, int IdServ) throws Exception {
        Employe e = employeDAO.findById(idEmp);
        Service s = serviceDAO.findById(IdServ);

        if (e.getService() != null && Objects.equals(e.getService().getId(), s.getId())) {
            throw new Exception("Employe is already in this service !");
        }
        e.setService(s);
        employeDAO.update(e);
    }
}
