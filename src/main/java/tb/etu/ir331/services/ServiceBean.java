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
public class ServiceBean implements IServiceBean {

    //SessionContext ctx;
    @EJB
    private ServiceDAO serviceDAO;
    @EJB
    private EmployeDAO employeDAO;

    @Override
    public void create(String name, String etat) throws Exception {
        this.create(name, etat, null);
    }

    @Override
    public void create(String name, String etat, Employe responsable) throws Exception {
        Service s = new Service();
        s.setNom(name);
        s.setEtat(etat);
        s.setResponsable(responsable);
        serviceDAO.persist(s);
    }

    @Override
    public Service getService(int id) throws Exception {
        return serviceDAO.findById(id);
    }

    @Override
    public List<Service> list() {
        return serviceDAO.findAll();
    }

    @Override
    public void setResp(int IdServ, int idEmp) throws Exception {
        Service s = serviceDAO.findById(IdServ);
        Employe e = employeDAO.findById(idEmp);
        
        //TODO verify if employe is in service 
        
        s.setResponsable(e);
        serviceDAO.update(s);
    }

    @Override
    public int getNbEmp(Service s) throws Exception {
        return employeDAO.findByServiceId(s.getId()).size();
    }
}
