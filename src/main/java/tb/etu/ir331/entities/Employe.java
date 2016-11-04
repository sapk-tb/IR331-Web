package tb.etu.ir331.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employe implements Serializable {

    @OneToOne(targetEntity = Contrat.class, mappedBy = "employé")
    private Contrat contrat;

    @Basic
    private String prénom;

    @ManyToOne(targetEntity = Service.class)
    private Service service;

    @Basic
    private String details;

    @Id
    private Long id;

    @Basic
    private String etat;

    @Basic
    private String nom;

    public Employe() {

    }

    public Contrat getContrat() {
        return this.contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public String getPrénom() {
        return this.prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
