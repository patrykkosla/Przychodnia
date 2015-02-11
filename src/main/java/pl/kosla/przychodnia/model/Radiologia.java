/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import pl.kosla.przychodnia.enums.RadiologyExamPart;
import pl.kosla.przychodnia.enums.RadiologyExamType;

/**
 *
 * @author patryk
 */
@Entity
@Table(name = "radiologia")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "Radiologia.findAll", query = "SELECT r FROM Radiologia r"),
   @NamedQuery(name = "Radiologia.findById", query = "SELECT r FROM Radiologia r WHERE r.id = :id"),
   @NamedQuery(name = "Radiologia.findByRTGOrderId", query = "SELECT r FROM Radiologia r WHERE r.examOrderId.id = :orderId"),
   @NamedQuery(name = "Radiologia.findByPatient", query = "SELECT r FROM Radiologia r WHERE r.patientId.patientId = :patientId"),
   @NamedQuery(name = "Radiologia.findByExamDate", query = "SELECT r FROM Radiologia r WHERE r.examDate = :examDate"),
   @NamedQuery(name = "Radiologia.findByImagesAmount", query = "SELECT r FROM Radiologia r WHERE r.imagesAmount = :imagesAmount"),
   @NamedQuery(name = "Radiologia.findByRadiologyExamType", query = "SELECT r FROM Radiologia r WHERE r.radiologyExamType = :radiologyExamType"),
   @NamedQuery(name = "Radiologia.findByRadiologyExamPart", query = "SELECT r FROM Radiologia r WHERE r.radiologyExamPart = :radiologyExamPart")})
public class Radiologia implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "id")
   private Integer id;
   @Column(name = "exam_date")
   @Temporal(TemporalType.TIMESTAMP)
   private Date examDate;
   @Lob
   @Size(max = 65535)
   @Column(name = "descriptione")
   private String descriptione;
   @Column(name = "images_amount")
   private Integer imagesAmount;
   @Enumerated(EnumType.STRING)
   @Column(name = "radiology_exam_type")
   private RadiologyExamType radiologyExamType;
   @Enumerated(EnumType.STRING)
   @Column(name = "radiology_exam_part")
   private RadiologyExamPart radiologyExamPart;
   @JoinColumn(name = "exam_order_id", referencedColumnName = "id")
   @ManyToOne
   private RadiologyExamOrder examOrderId;
   @JoinColumn(name = "medic_id", referencedColumnName = "id")
   @ManyToOne(optional = false)
   private Medic medicId;
   @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
   @ManyToOne(optional = false)
   private Patient patientId;

   public Radiologia() {
   }

   public Radiologia(Integer id) {
      this.id = id;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Date getExamDate() {
      return examDate;
   }

   public void setExamDate(Date examDate) {
      this.examDate = examDate;
   }

   public String getDescriptione() {
      return descriptione;
   }

   public void setDescriptione(String descriptione) {
      this.descriptione = descriptione;
   }

   public Integer getImagesAmount() {
      return imagesAmount;
   }

   public void setImagesAmount(Integer imagesAmount) {
      this.imagesAmount = imagesAmount;
   }

   public RadiologyExamType getRadiologyExamType() {
      return radiologyExamType;
   }

   public void setRadiologyExamType(RadiologyExamType radiologyExamType) {
      this.radiologyExamType = radiologyExamType;
   }

   public RadiologyExamPart getRadiologyExamPart() {
      return radiologyExamPart;
   }

   public void setRadiologyExamPart(RadiologyExamPart radiologyExamPart) {
      this.radiologyExamPart = radiologyExamPart;
   }

   public RadiologyExamOrder getExamOrderId() {
      return examOrderId;
   }

   public void setExamOrderId(RadiologyExamOrder examOrderId) {
      this.examOrderId = examOrderId;
   }

   public Medic getMedicId() {
      return medicId;
   }

   public void setMedicId(Medic medicId) {
      this.medicId = medicId;
   }

   public Patient getPatientId() {
      return patientId;
   }

   public void setPatientId(Patient patientId) {
      this.patientId = patientId;
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
      if (!(object instanceof Radiologia)) {
         return false;
      }
      Radiologia other = (Radiologia) object;
      if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "model.Radiologia[ id=" + id + " ]";
   }
   
}
