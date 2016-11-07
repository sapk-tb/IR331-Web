package tb.etu.ir331.services;

import java.util.List;
import javax.ejb.Remote;
import tb.etu.ir331.entities.Contrat;

/**
 *
 * @author sapk
 */
@Remote
public interface IContratBean {

    public void create(int empId, String type) throws Exception;

    public void create(int empId, String type, String etat, String startDate, String endDate) throws Exception ;

    public List<Contrat> list();

    public void sign(int id) throws Exception;

    public Contrat getContrat(int id) throws Exception;
}
