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

    void create(String name, String etat);
    void create(String name, String etat, Employe responsable);

    public Service getService(int id);

    public List<Service> list();

}
