/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author patryk
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Holidays.findAll", query = "SELECT h FROM Holidays h"),
    @NamedQuery(name = "Holidays.findById", query = "SELECT h FROM Holidays h WHERE h.id = :id"),
    @NamedQuery(name = "Holidays.findBymedicIdStatusDay",
    query = "SELECT h FROM Holidays h WHERE h.surgeryHasMedicId.medicId.id = :medicId AND h.status IN ( :status , :statusBis)  AND  :date BETWEEN  h.startDate AND h.endDate"),
    @NamedQuery(name = "Holidays.checkIfDoctorIs",
    query = "SELECT h FROM Holidays h WHERE h.surgeryHasMedicId.medicId.id = :medicId AND :date BETWEEN  h.startDate AND h.endDate"),
    
    @NamedQuery(name = "Holidays.findByStartDate", query = "SELECT h FROM Holidays h WHERE h.startDate = :startDate"),
    @NamedQuery(name = "Holidays.findByEndDate", query = "SELECT h FROM Holidays h WHERE h.endDate = :endDate"),
    @NamedQuery(name = "Holidays.findByStatus", query = "SELECT h FROM Holidays h WHERE h.status = :status"),
    @NamedQuery(name = "Holidays.findByApplyDate", query = "SELECT h FROM Holidays h WHERE h.applyDate = :applyDate")})
public class Holidays implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Size(max = 9)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date applyDate;
    
    @JoinColumn(name = "surgery_has_medic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SurgeryHasMedic surgeryHasMedicId;

    public Holidays() {
    }

    public Holidays(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public SurgeryHasMedic getSurgeryHasMedicId() {
        return surgeryHasMedicId;
    }

    public void setSurgeryHasMedicId(SurgeryHasMedic surgeryHasMedicId) {
        this.surgeryHasMedicId = surgeryHasMedicId;
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
        if (!(object instanceof Holidays)) {
            return false;
        }
        Holidays other = (Holidays) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Holidays[ id=" + id + " ]";
    }
    
}
