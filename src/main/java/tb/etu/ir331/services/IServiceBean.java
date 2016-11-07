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

    Service create(String name, String etat) throws Exception;

    Service create(String name, String etat, Employe responsable) throws Exception;

    Service setResp(int IdServ, int idEmp) throws Exception;

    public Service getService(int id) throws Exception;

    public List<Service> list();
    
    public List<Employe> getEmpList(Service s) throws Exception;
    public int getNbEmp(Service s) throws Exception;
    

}
