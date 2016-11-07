package tb.etu.ir331.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author sapk
 */
@Entity
public class Contrat implements Serializable {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //new SimpleDateFormat("dd/MM/yyyy");
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String etat;

    @Basic
    private String type;

    @Basic
    private String startDate;

    @Basic
    private String endDate;

    @OneToOne(targetEntity = Employe.class)
    private Employe employe;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtat() { // waitsign, active, closed
        return this.etat; //TODO if CDD and endDate passed -> closed
    }
    
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Employe getEmploye() {
        return this.employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    @Override
    public String toString() {
        return "Contrat{" + "id=" + id + ", etat=" + etat + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate + ", employe=" + employe.getId() + '}';
    }

}
