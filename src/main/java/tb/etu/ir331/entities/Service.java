package tb.etu.ir331.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author sapk
 */
@Entity
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String nom;

    @Basic
    private String etat;

    @OneToOne(targetEntity = Employe.class)
    private Employe responsable;

    //TODO
    @ManyToOne(targetEntity = Service.class)
    private Service parent;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Employe getResponsable() {
        return this.responsable;
    }

    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    public Service getParent() {
        return this.parent;
    }

    public void setParent(Service parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        String resp = "null";
        String par = "null";
        if(responsable != null){
            resp = ""+responsable.getId();
        }
        if(parent != null){
            par = ""+parent.getId();
        }
        return "Service{" + "id=" + id + ", nom=" + nom + ", etat=" + etat + ", responsable=" + resp + ", parent=" + par + '}';
    }

}
