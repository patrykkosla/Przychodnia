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
@Table(name = "sick_leave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SickLeave.findAll", query = "SELECT s FROM SickLeave s"),
    @NamedQuery(name = "SickLeave.findById", query = "SELECT s FROM SickLeave s WHERE s.id = :id"),
    @NamedQuery(name = "SickLeave.findByAppoitmentId", query = "SELECT s FROM SickLeave s WHERE s.appoitmentId.id = :appoitmentId"),
    @NamedQuery(name = "SickLeave.findByPatientId", query = "SELECT s FROM SickLeave s WHERE s.appoitmentId.patientId.patientId = :patientId"),
    @NamedQuery(name = "SickLeave.findByDateFrom", query = "SELECT s FROM SickLeave s WHERE s.dateFrom = :dateFrom"),
    @NamedQuery(name = "SickLeave.findByDateTo", query = "SELECT s FROM SickLeave s WHERE s.dateTo = :dateTo"),
    @NamedQuery(name = "SickLeave.findBySecureCode", query = "SELECT s FROM SickLeave s WHERE s.secureCode = :secureCode")})
public class SickLeave implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Column(name = "date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @Size(max = 9)
    private String secureCode;
    
    @JoinColumn(name = "appoitment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Appoitment appoitmentId;

    public SickLeave() {
    }

    public SickLeave(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
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
        if (!(object instanceof SickLeave)) {
            return false;
        }
        SickLeave other = (SickLeave) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.SickLeave[ id=" + id + " ]";
    }
    
}
