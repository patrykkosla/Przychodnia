/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author patryk
 */
@Entity
@Table(name = "referral_appoitment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReferralAppoitment.findAll", query = "SELECT r FROM ReferralAppoitment r"),
    @NamedQuery(name = "ReferralAppoitment.findById", query = "SELECT r FROM ReferralAppoitment r WHERE r.id = :id")})
public class ReferralAppoitment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Lob
    @Size(max = 65535)
    private String opis;
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    @ManyToOne
    private Diagnose diagnoseId;
    @JoinColumn(name = "appoitment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Appoitment appoitmentId;

    public ReferralAppoitment() {
    }

    public ReferralAppoitment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Diagnose getDiagnoseId() {
        return diagnoseId;
    }

    public void setDiagnoseId(Diagnose diagnoseId) {
        this.diagnoseId = diagnoseId;
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
        if (!(object instanceof ReferralAppoitment)) {
            return false;
        }
        ReferralAppoitment other = (ReferralAppoitment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.ReferralAppoitment[ id=" + id + " ]";
    }
    
}
