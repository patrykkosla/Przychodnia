/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author patryk
 */
@Entity
@Table(name = "surgery_has_medic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurgeryHasMedic.findAll", query = "SELECT s FROM SurgeryHasMedic s"),
    @NamedQuery(name = "SurgeryHasMedic.findById", query = "SELECT s FROM SurgeryHasMedic s WHERE s.id = :id"),
    @NamedQuery(name = "SurgeryHasMedic.findBySecureLevel", query = "SELECT s FROM SurgeryHasMedic s WHERE s.secureLevel = :secureLevel"),
    @NamedQuery(name = "SurgeryHasMedic.findByPositione", query = "SELECT s FROM SurgeryHasMedic s WHERE s.positione = :positione"),
    @NamedQuery(name = "SurgeryHasMedic.findByIsAtive", query = "SELECT s FROM SurgeryHasMedic s WHERE s.isAtive = :isAtive"),
    @NamedQuery(name = "SurgeryHasMedic.findByCreationTime", query = "SELECT s FROM SurgeryHasMedic s WHERE s.creationTime = :creationTime")})
public class SurgeryHasMedic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private Integer secureLevel;
    @Size(max = 3)
    private String positione;
    @Column(name = "is_ative")
    private Boolean isAtive;
    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @JoinColumn(name = "surgery_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Surgery surgeryId;
    @JoinColumn(name = "medic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medic medicId;

    public SurgeryHasMedic() {
    }

    public SurgeryHasMedic(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSecureLevel() {
        return secureLevel;
    }

    public void setSecureLevel(Integer secureLevel) {
        this.secureLevel = secureLevel;
    }

    public String getPositione() {
        return positione;
    }

    public void setPositione(String positione) {
        this.positione = positione;
    }

    public Boolean getIsAtive() {
        return isAtive;
    }

    public void setIsAtive(Boolean isAtive) {
        this.isAtive = isAtive;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Surgery getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(Surgery surgeryId) {
        this.surgeryId = surgeryId;
    }

    public Medic getMedicId() {
        return medicId;
    }

    public void setMedicId(Medic medicId) {
        this.medicId = medicId;
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
        if (!(object instanceof SurgeryHasMedic)) {
            return false;
        }
        SurgeryHasMedic other = (SurgeryHasMedic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.SurgeryHasMedic[ id=" + id + " ]";
    }
    
}
