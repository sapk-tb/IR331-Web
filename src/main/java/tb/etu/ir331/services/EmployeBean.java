package tb.etu.ir331.services;

import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tb.etu.ir331.dao.ContratDAO;
import tb.etu.ir331.dao.EmployeDAO;
import tb.etu.ir331.dao.ServiceDAO;
import tb.etu.ir331.entities.Contrat;
import tb.etu.ir331.entities.Employe;
import tb.etu.ir331.entities.Service;

/**
 *
 * @author sapk
 */
@Stateless
@LocalBean
public class EmployeBean implements IEmployeBean {

    @EJB
    private EmployeDAO employeDAO;
    @EJB
    private ServiceDAO serviceDAO;
    @EJB
    private ContratDAO contratDAO;

    @Override
    public void create(String prenom, String nom) throws Exception{
        this.create(prenom, nom, null);
    }

    @Override
    public void create(String prenom, String nom, Service service) throws Exception{
        Employe e = new Employe();
        e.setPrenom(prenom);
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
       
        //Check if not in sergice allready
        if (e.getService() != null && Objects.equals(e.getService().getId(), s.getId())) {
            throw new Exception("Employe is already in this service !");
        }
        //Check that he is not a chief of another service
        if(serviceDAO.findByResp(idEmp).size()>0){
            throw new Exception("The employe is the chief a service. Please remove from it before.");
        }
        e.setService(s);
        employeDAO.update(e);
    }
    
    @Override
    public List<Contrat> getContratList(Employe e) throws Exception {
        return contratDAO.findByEmployeId(e.getId());
    }
    @Override
    public int getNbContrat(Employe e) throws Exception {
        return contratDAO.findByEmployeId(e.getId()).size();
    }
}
