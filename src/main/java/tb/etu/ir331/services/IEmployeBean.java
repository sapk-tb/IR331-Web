package tb.etu.ir331.services;

import java.util.List;
import javax.ejb.Remote;
import tb.etu.ir331.entities.Contrat;
import tb.etu.ir331.entities.Employe;
import tb.etu.ir331.entities.Service;
/**
 *
 * @author sapk
 */
@Remote
public interface IEmployeBean {

    void create(String prenom, String nom) throws Exception;
    void create(String prenom, String nom, Service service) throws Exception;
    void attach(int idEmp, int IdServ) throws Exception;

    public Employe getEmploye(int id) throws Exception;

    public List<Employe> list();
    
    public List<Contrat> getContratList(Employe e) throws Exception;
    public int getNbContrat(Employe e) throws Exception;

}
