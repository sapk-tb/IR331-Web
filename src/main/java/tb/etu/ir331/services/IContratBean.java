package tb.etu.ir331.services;

import javax.ejb.Remote;
import tb.etu.ir331.entities.Contrat;

/**
 *
 * @author sapk
 */
@Remote
public interface IContratBean {

    void create();


    public Contrat[] list();

}
