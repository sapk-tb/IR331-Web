package tb.etu.ir331.services;

import javax.ejb.Remote;
import tb.etu.ir331.entities.Employe;

/**
 *
 * @author sapk
 */
@Remote
public interface IEmployeBean {

    void create(String name);

    public Employe[] list();
    
}
