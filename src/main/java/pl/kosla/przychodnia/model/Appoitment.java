/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Appoitment.findAll", query = "SELECT a FROM Appoitment a"),
    @NamedQuery(name = "Appoitment.findById", query = "SELECT a FROM Appoitment a WHERE a.id = :id"),
    @NamedQuery(name = "Appoitment.findByAppoitmentDate", query = "SELECT a FROM Appoitment a WHERE a.appoitmentDate = :appoitmentDate"),
    @NamedQuery(name = "Appoitment.findByQueuePositione", query = "SELECT a FROM Appoitment a WHERE a.queuePositione = :queuePositione"),
    @NamedQuery(name = "Appoitment.findByApproximateTime", query = "SELECT a FROM Appoitment a WHERE a.approximateTime = :approximateTime"),
    @NamedQuery(name = "Appoitment.findByStatus", query = "SELECT a FROM Appoitment a WHERE a.status = :status"),
    @NamedQuery(name = "Appoitment.findByRadiologyTestOrder", query = "SELECT a FROM Appoitment a WHERE a.radiologyTestOrder = :radiologyTestOrder"),
    @NamedQuery(name = "Appoitment.findByBlodTestOrder", query = "SELECT a FROM Appoitment a WHERE a.blodTestOrder = :blodTestOrder")})
public class Appoitment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Column(name = "appoitment_date")
    @Temporal(TemporalType.DATE)
    private Date appoitmentDate;
    @Column(name = "queue_positione")
    private Integer queuePositione;
    @Column(name = "approximate_time")
    @Temporal(TemporalType.TIME)
    private Date approximateTime;
    @Size(max = 3)
    private String status;
    @Lob
    @Size(max = 65535)
    private String note;
    @Size(max = 220)
    @Column(name = "radiology_test_order")
    private String radiologyTestOrder;
    @Size(max = 45)
    @Column(name = "blod_test_order")
    private String blodTestOrder;
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    @ManyToOne(optional = false)
    private Patient patientId;
    @JoinColumn(name = "medic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medic medicId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appoitmentId")
    private Collection<SickLeave> sickLeaveCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appoitmentId")
    private Collection<ReferralAppoitment> referralAppoitmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appoitmentId")
    private Collection<Diagnose> diagnoseCollection;

    public Appoitment() {
    }

    public Appoitment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAppoitmentDate() {
        return appoitmentDate;
    }

    public void setAppoitmentDate(Date appoitmentDate) {
        this.appoitmentDate = appoitmentDate;
    }

    public Integer getQueuePositione() {
        return queuePositione;
    }

    public void setQueuePositione(Integer queuePositione) {
        this.queuePositione = queuePositione;
    }

    public Date getApproximateTime() {
        return approximateTime;
    }

    public void setApproximateTime(Date approximateTime) {
        this.approximateTime = approximateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRadiologyTestOrder() {
        return radiologyTestOrder;
    }

    public void setRadiologyTestOrder(String radiologyTestOrder) {
        this.radiologyTestOrder = radiologyTestOrder;
    }

    public String getBlodTestOrder() {
        return blodTestOrder;
    }

    public void setBlodTestOrder(String blodTestOrder) {
        this.blodTestOrder = blodTestOrder;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Medic getMedicId() {
        return medicId;
    }

    public void setMedicId(Medic medicId) {
        this.medicId = medicId;
    }

    @XmlTransient
    public Collection<SickLeave> getSickLeaveCollection() {
        return sickLeaveCollection;
    }

    public void setSickLeaveCollection(Collection<SickLeave> sickLeaveCollection) {
        this.sickLeaveCollection = sickLeaveCollection;
    }

    @XmlTransient
    public Collection<ReferralAppoitment> getReferralAppoitmentCollection() {
        return referralAppoitmentCollection;
    }

    public void setReferralAppoitmentCollection(Collection<ReferralAppoitment> referralAppoitmentCollection) {
        this.referralAppoitmentCollection = referralAppoitmentCollection;
    }

    @XmlTransient
    public Collection<Diagnose> getDiagnoseCollection() {
        return diagnoseCollection;
    }

    public void setDiagnoseCollection(Collection<Diagnose> diagnoseCollection) {
        this.diagnoseCollection = diagnoseCollection;
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
        if (!(object instanceof Appoitment)) {
            return false;
        }
        Appoitment other = (Appoitment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Appoitment[ id=" + id + " ]";
    }
    
}
