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
    public Service getService(int id) throws Exception {
        return serviceDAO.findById(id);
    }

    @Override
    public List<Service> list() {
        return serviceDAO.findAll();
    }

    @Override
    public Service setResp(int IdServ, int idEmp) throws Exception {
        Service s = serviceDAO.findById(IdServ);
        Employe e = employeDAO.findById(idEmp);

        //Verify if employe is in service to become chief 
        if (e.getService() == null || !Objects.equals(e.getService().getId(), s.getId())) {
            throw new Exception("Employe is not in this service staff !");
        }
        /* Not necessary since user shuold not be in a another service
        //Verifiy that he is not chief of another service 
        for (Service tmp : serviceDAO.findAll()) {
            if(tmp.getResponsable() != null && Objects.equals(e.getId(), tmp.getResponsable().getId())){
               throw new Exception("Employe is already chief of the service : "+tmp.getNom());
            }
        }
         */
        s.setResponsable(e);
        return serviceDAO.update(s);
    }

    @Override
    public List<Employe> getEmpList(Service s) throws Exception {
        return employeDAO.findByServiceId(s.getId());
    }

    @Override
    public int getNbEmp(Service s) throws Exception {
        return employeDAO.findByServiceId(s.getId()).size();
    }

    @Override
    public Service create(String name, String etat, Service parent, Employe responsable) throws Exception {
        Service s = new Service();
        s.setNom(name);
        s.setEtat(etat);
        s.setParent(parent);
        s.setResponsable(responsable);
        return serviceDAO.persist(s);
    }

    @Override
    public Service create(String name, String etat, String parentId, String responsableId) throws Exception {

        Employe resp = null;
        if (responsableId != null && !responsableId.equals("-1") && !responsableId.equals("")) {
            resp = employeDAO.findById(new Integer(responsableId));
        }
        Service parent = null;
        if (parentId != null && !parentId.equals("-1") && !parentId.equals("")) {
            parent = serviceDAO.findById(new Integer(parentId));
        }
        return this.create(name, etat, parent, resp);
    }
    @Override
    public Service setParent(String IdServ, String idParent) throws Exception {
        Service s = serviceDAO.findById(new Integer(IdServ));
        Service p = null;
        if (idParent != null && !idParent.equals("-1") && !idParent.equals("")) {
            p = serviceDAO.findById(new Integer(idParent));
        }

        s.setParent(p);
        return serviceDAO.update(s);
    }
    /*
    @Override
    public Service create(String name, String etat) throws Exception {
        return this.create(name, etat, null);
    }

    @Override
    public Service create(String name, String etat, Employe responsable) throws Exception {
        Service s = new Service();
        s.setNom(name);
        s.setEtat(etat);
        s.setResponsable(responsable);
        return serviceDAO.persist(s);
    }
     */
}
