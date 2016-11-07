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

    public Contrat create(int empId, String type) throws Exception;

    public Contrat create(int empId, String type, String etat, String startDate, String endDate) throws Exception;

    public List<Contrat> list();

    public Contrat sign(int id) throws Exception;

    public Contrat close(int id) throws Exception;

    public Contrat getContrat(int id) throws Exception;
}
