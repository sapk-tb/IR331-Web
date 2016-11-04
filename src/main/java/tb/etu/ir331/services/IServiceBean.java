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

    void create(String name, String etat) throws Exception;
    void create(String name, String etat, Employe responsable) throws Exception;

    public Service getService(int id) throws Exception ;

    public List<Service> list();

}
