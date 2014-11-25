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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author patryk
 */
@Entity
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "Workhour.findAll", query = "SELECT w FROM Workhour w"),
   @NamedQuery(name = "Workhour.findById", query = "SELECT w FROM Workhour w WHERE w.id = :id"),
   @NamedQuery(name = "Workhour.findByMedicId&SurgeryId",
   query = "SELECT w FROM Workhour w WHERE w.surgeryHasMedicId.medicId.id = :medicId AND w.surgeryHasMedicId.surgeryId.id = :surgeryId AND w.surgeryHasMedicId.isAtive =: isActive"),

   @NamedQuery(name = "Workhour.findByMonday", query = "SELECT w FROM Workhour w WHERE w.monday = :monday"),
   @NamedQuery(name = "Workhour.findByMondayType", query = "SELECT w FROM Workhour w WHERE w.mondayType = :mondayType"),
   @NamedQuery(name = "Workhour.findByMondayStart", query = "SELECT w FROM Workhour w WHERE w.mondayStart = :mondayStart"),
   @NamedQuery(name = "Workhour.findByMondayEnd", query = "SELECT w FROM Workhour w WHERE w.mondayEnd = :mondayEnd"),
   @NamedQuery(name = "Workhour.findByTuesday", query = "SELECT w FROM Workhour w WHERE w.tuesday = :tuesday"),
   @NamedQuery(name = "Workhour.findByTuesdayType", query = "SELECT w FROM Workhour w WHERE w.tuesdayType = :tuesdayType"),
   @NamedQuery(name = "Workhour.findByTuesdayStart", query = "SELECT w FROM Workhour w WHERE w.tuesdayStart = :tuesdayStart"),
   @NamedQuery(name = "Workhour.findByTuesdayEnd", query = "SELECT w FROM Workhour w WHERE w.tuesdayEnd = :tuesdayEnd"),
   @NamedQuery(name = "Workhour.findByWednesday", query = "SELECT w FROM Workhour w WHERE w.wednesday = :wednesday"),
   @NamedQuery(name = "Workhour.findByWednesdayType", query = "SELECT w FROM Workhour w WHERE w.wednesdayType = :wednesdayType"),
   @NamedQuery(name = "Workhour.findByWednesdayStart", query = "SELECT w FROM Workhour w WHERE w.wednesdayStart = :wednesdayStart"),
   @NamedQuery(name = "Workhour.findByWednesdayEnd", query = "SELECT w FROM Workhour w WHERE w.wednesdayEnd = :wednesdayEnd"),
   @NamedQuery(name = "Workhour.findByThursday", query = "SELECT w FROM Workhour w WHERE w.thursday = :thursday"),
   @NamedQuery(name = "Workhour.findByThursdayType", query = "SELECT w FROM Workhour w WHERE w.thursdayType = :thursdayType"),
   @NamedQuery(name = "Workhour.findByThursdayStart", query = "SELECT w FROM Workhour w WHERE w.thursdayStart = :thursdayStart"),
   @NamedQuery(name = "Workhour.findByThursdayEnd", query = "SELECT w FROM Workhour w WHERE w.thursdayEnd = :thursdayEnd"),
   @NamedQuery(name = "Workhour.findByFriday", query = "SELECT w FROM Workhour w WHERE w.friday = :friday"),
   @NamedQuery(name = "Workhour.findByFridayType", query = "SELECT w FROM Workhour w WHERE w.fridayType = :fridayType"),
   @NamedQuery(name = "Workhour.findByFridayStart", query = "SELECT w FROM Workhour w WHERE w.fridayStart = :fridayStart"),
   @NamedQuery(name = "Workhour.findByFridayEnd", query = "SELECT w FROM Workhour w WHERE w.fridayEnd = :fridayEnd")})
public class Workhour implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   private Integer id;
   private Boolean monday;
   @Size(max = 9)
   private String mondayType;
   @Temporal(TemporalType.TIME)
   private Date mondayStart;
   @Temporal(TemporalType.TIME)
   private Date mondayEnd;
   private Boolean tuesday;
   @Size(max = 9)
   private String tuesdayType;
   @Temporal(TemporalType.TIME)
   private Date tuesdayStart;
   @Temporal(TemporalType.TIME)
   private Date tuesdayEnd;
   private Boolean wednesday;
   @Size(max = 9)
   private String wednesdayType;
   @Temporal(TemporalType.TIME)
   private Date wednesdayStart;
   @Temporal(TemporalType.TIME)
   private Date wednesdayEnd;
   private Boolean thursday;
   @Size(max = 9)
   private String thursdayType;
   @Temporal(TemporalType.TIME)
   private Date thursdayStart;
   @Temporal(TemporalType.TIME)
   private Date thursdayEnd;
   private Boolean friday;
   @Size(max = 9)
   private String fridayType;
   @Temporal(TemporalType.TIME)
   private Date fridayStart;
   @Temporal(TemporalType.TIME)
   private Date fridayEnd;
   @JoinColumn(name = "surgery_has_medic_id", referencedColumnName = "id")
   @ManyToOne(optional = false)
   private SurgeryHasMedic surgeryHasMedicId;
   
   public Workhour() {
   }

   public Workhour(Integer id) {
      this.id = id;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Boolean getMonday() {
      return monday;
   }

   public void setMonday(Boolean monday) {
      this.monday = monday;
   }

   public String getMondayType() {
      return mondayType;
   }

   public void setMondayType(String mondayType) {
      this.mondayType = mondayType;
   }

   public Date getMondayStart() {
      return mondayStart;
   }

   public void setMondayStart(Date mondayStart) {
      this.mondayStart = mondayStart;
   }

   public Date getMondayEnd() {
      return mondayEnd;
   }

   public void setMondayEnd(Date mondayEnd) {
      this.mondayEnd = mondayEnd;
   }

   public Boolean getTuesday() {
      return tuesday;
   }

   public void setTuesday(Boolean tuesday) {
      this.tuesday = tuesday;
   }

   public String getTuesdayType() {
      return tuesdayType;
   }

   public void setTuesdayType(String tuesdayType) {
      this.tuesdayType = tuesdayType;
   }

   public Date getTuesdayStart() {
      return tuesdayStart;
   }

   public void setTuesdayStart(Date tuesdayStart) {
      this.tuesdayStart = tuesdayStart;
   }

   public Date getTuesdayEnd() {
      return tuesdayEnd;
   }

   public void setTuesdayEnd(Date tuesdayEnd) {
      this.tuesdayEnd = tuesdayEnd;
   }

   public Boolean getWednesday() {
      return wednesday;
   }

   public void setWednesday(Boolean wednesday) {
      this.wednesday = wednesday;
   }

   public String getWednesdayType() {
      return wednesdayType;
   }

   public void setWednesdayType(String wednesdayType) {
      this.wednesdayType = wednesdayType;
   }

   public Date getWednesdayStart() {
      return wednesdayStart;
   }

   public void setWednesdayStart(Date wednesdayStart) {
      this.wednesdayStart = wednesdayStart;
   }

   public Date getWednesdayEnd() {
      return wednesdayEnd;
   }

   public void setWednesdayEnd(Date wednesdayEnd) {
      this.wednesdayEnd = wednesdayEnd;
   }

   public Boolean getThursday() {
      return thursday;
   }

   public void setThursday(Boolean thursday) {
      this.thursday = thursday;
   }

   public String getThursdayType() {
      return thursdayType;
   }

   public void setThursdayType(String thursdayType) {
      this.thursdayType = thursdayType;
   }

   public Date getThursdayStart() {
      return thursdayStart;
   }

   public void setThursdayStart(Date thursdayStart) {
      this.thursdayStart = thursdayStart;
   }

   public Date getThursdayEnd() {
      return thursdayEnd;
   }

   public void setThursdayEnd(Date thursdayEnd) {
      this.thursdayEnd = thursdayEnd;
   }

   public Boolean getFriday() {
      return friday;
   }

   public void setFriday(Boolean friday) {
      this.friday = friday;
   }

   public String getFridayType() {
      return fridayType;
   }

   public void setFridayType(String fridayType) {
      this.fridayType = fridayType;
   }

   public Date getFridayStart() {
      return fridayStart;
   }

   public void setFridayStart(Date fridayStart) {
      this.fridayStart = fridayStart;
   }

   public Date getFridayEnd() {
      return fridayEnd;
   }

   public void setFridayEnd(Date fridayEnd) {
      this.fridayEnd = fridayEnd;
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
      if (!(object instanceof Workhour)) {
         return false;
      }
      Workhour other = (Workhour) object;
      if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "pl.kosla.przychodnia.model.Workhour[ id=" + id + " ]";
   }
   
}
