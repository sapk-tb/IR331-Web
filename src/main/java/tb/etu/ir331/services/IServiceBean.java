package tb.etu.ir331.services;

import java.util.List;
import javax.ejb.Remote;
import tb.etu.ir331.entities.Employe;
import tb.etu.ir331.entities.Service;

/**
 *
 * @author sapk
 */
@Remote
public interface IServiceBean {

    //Service create(String name, String etat) throws Exception;

    Service create(String name, String etat, Service parent, Employe responsable) throws Exception;
    Service create(String name, String etat, String parentId, String responsableId) throws Exception;

    Service setResp(int IdServ, int idEmp) throws Exception;
    Service setParent(String IdServ, String idParent) throws Exception;

    public Service getService(int id) throws Exception;

    public List<Service> list();
    
    public List<Employe> getEmpList(Service s) throws Exception;
    public int getNbEmp(Service s) throws Exception;
    

}
