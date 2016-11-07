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
public class Employe implements Serializable {

    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String etat;

    @Basic
    private String details;

    @Basic
    private String nom;

    @Basic
    private String prenom;

    @OneToOne(targetEntity = Contrat.class, mappedBy = "employe")
    private Contrat contrat;

    @ManyToOne(targetEntity = Service.class)
    private Service service;

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

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Contrat getContrat() {
        return this.contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", etat=" + etat + ", details=" + details + ", nom=" + nom + ", prenom=" + prenom + ", contrat=" + contrat.getId() + ", service=" + service.getId() + '}';
    }

}
