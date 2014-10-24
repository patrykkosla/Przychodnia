/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author patryk
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnose.findAll", query = "SELECT d FROM Diagnose d"),
    @NamedQuery(name = "Diagnose.findById", query = "SELECT d FROM Diagnose d WHERE d.id = :id"),
    @NamedQuery(name = "Diagnose.findByMarkAsImportant", query = "SELECT d FROM Diagnose d WHERE d.markAsImportant = :markAsImportant")})
public class Diagnose implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Lob
    @Size(max = 65535)
    private String diagnosis;
    @Column(name = "mark_as_important")
    private Boolean markAsImportant;
    @OneToMany(mappedBy = "diagnoseId")
    private Collection<ReferralAppoitment> referralAppoitmentCollection;
    @JoinColumn(name = "sickness_fk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sicknessdic sicknessFk;
    @JoinColumn(name = "appoitment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Appoitment appoitmentId;

    public Diagnose() {
    }

    public Diagnose(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Boolean getMarkAsImportant() {
        return markAsImportant;
    }

    public void setMarkAsImportant(Boolean markAsImportant) {
        this.markAsImportant = markAsImportant;
    }

    @XmlTransient
    public Collection<ReferralAppoitment> getReferralAppoitmentCollection() {
        return referralAppoitmentCollection;
    }

    public void setReferralAppoitmentCollection(Collection<ReferralAppoitment> referralAppoitmentCollection) {
        this.referralAppoitmentCollection = referralAppoitmentCollection;
    }

    public Sicknessdic getSicknessFk() {
        return sicknessFk;
    }

    public void setSicknessFk(Sicknessdic sicknessFk) {
        this.sicknessFk = sicknessFk;
    }

    public Appoitment getAppoitmentId() {
        return appoitmentId;
    }

    public void setAppoitmentId(Appoitment appoitmentId) {
        this.appoitmentId = appoitmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnose)) {
            return false;
        }
        Diagnose other = (Diagnose) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Diagnose[ id=" + id + " ]";
    }
    
}
