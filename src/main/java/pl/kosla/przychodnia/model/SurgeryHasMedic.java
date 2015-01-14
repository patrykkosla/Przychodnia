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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pl.kosla.przychodnia.enums.Positione;

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
    @NamedQuery(name = "SurgeryHasMedic.findByOnHoliday", query = "SELECT s FROM SurgeryHasMedic s WHERE s.onHoliday = :onHoliday"),
    @NamedQuery(name = "SurgeryHasMedic.findByCreationTime", query = "SELECT s FROM SurgeryHasMedic s WHERE s.creationTime = :creationTime"),
    @NamedQuery(name = "SurgeryHasMedic.findByStartDate", query = "SELECT s FROM SurgeryHasMedic s WHERE s.startDate = :startDate"), 
   @NamedQuery(name = "SurgeryHasMedic.finaByPositineIsActive", 
   query = "SELECT s FROM SurgeryHasMedic s WHERE s.positione = :positione AND  s.isAtive = :isAtive"), 
   @NamedQuery(name = "SurgeryHasMedic.findSugeryForMedic",
   query = "SELECT shm.surgeryId FROM SurgeryHasMedic shm WHERE shm.medicId = :medicId"),
   @NamedQuery(name = "SurgeryHasMedic.findMedicForSurgery",
   query = "SELECT shm.medicId FROM SurgeryHasMedic shm WHERE shm.surgeryId = :surgeryId AND shm.isAtive = :isAtive" ),
   @NamedQuery(name = "SurgeryHasMedic.findActiveSurgeryforMedic",
   query = "SELECT shm.surgeryId FROM SurgeryHasMedic shm WHERE shm.medicId.id = :medicId AND shm.isAtive = :isAtive" ),
   
   @NamedQuery(name = "SurgeryHasMedic.findByStopDate", query = "SELECT s FROM SurgeryHasMedic s WHERE s.stopDate = :stopDate")})
public class SurgeryHasMedic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private Integer secureLevel;
    
    @Enumerated(EnumType.STRING)
    private Positione positione;
    
    @Column(name = "is_ative")
    private Boolean isAtive;
    private Boolean onHoliday;
    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date stopDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surgeryHasMedicId")
    private Collection<Holidays> holidaysCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surgeryHasMedicId")
    private Collection<Workhour> workhourCollection;
    
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

    public Positione getPositione() {
        return positione;
    }

    public void setPositione(Positione positione) {
        this.positione = positione;
    }

    public Boolean getIsAtive() {
        return isAtive;
    }

    public void setIsAtive(Boolean isAtive) {
        this.isAtive = isAtive;
    }

    public Boolean getOnHoliday() {
        return onHoliday;
    }

    public void setOnHoliday(Boolean onHoliday) {
        this.onHoliday = onHoliday;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    @XmlTransient
    public Collection<Holidays> getHolidaysCollection() {
        return holidaysCollection;
    }

    public void setHolidaysCollection(Collection<Holidays> holidaysCollection) {
        this.holidaysCollection = holidaysCollection;
    }

    public Collection<Workhour> getWorkhourCollection() {
        return workhourCollection;
    }

    public void setWorkhourCollection(Collection<Workhour> workhourCollection) {
        this.workhourCollection = workhourCollection;
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
