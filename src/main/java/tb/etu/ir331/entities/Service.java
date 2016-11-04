package tb.etu.ir331.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Service implements Serializable {

    @OneToOne(targetEntity = Employe.class)
    private Employe responsable;

    @Id
    private Long id;

    @Basic
    private String nom;

    @Basic
    private String etat;

    public Service() {

    }

    public Employe getResponsable() {
        return this.responsable;
    }

    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.setId(new Long(id));
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
}
