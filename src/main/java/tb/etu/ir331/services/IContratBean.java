package tb.etu.ir331.services;

import java.util.List;
import javax.ejb.Remote;
import tb.etu.ir331.entities.Contrat;
import tb.etu.ir331.entities.Employe;

/**
 *
 * @author sapk
 */
@Remote
public interface IContratBean {

    public void create(Employe emp, String type) throws Exception;
    public void create(Employe emp, String type, String etat) throws Exception;

    public List<Contrat> list();

    public Contrat getContrat(int id) throws Exception;
}
