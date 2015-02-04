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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author patryk
 */
@Entity
@Table(name = "blood_test")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "BloodTest.findAll", query = "SELECT b FROM BloodTest b"),
   @NamedQuery(name = "BloodTest.findById", query = "SELECT b FROM BloodTest b WHERE b.id = :id"),
   @NamedQuery(name = "BloodTest.findByExamDate", query = "SELECT b FROM BloodTest b WHERE b.examDate = :examDate"),
   @NamedQuery(name = "BloodTest.findByHemoglobine", query = "SELECT b FROM BloodTest b WHERE b.hemoglobine = :hemoglobine"),
   @NamedQuery(name = "BloodTest.findByErytrocyt", query = "SELECT b FROM BloodTest b WHERE b.erytrocyt = :erytrocyt"),
   @NamedQuery(name = "BloodTest.findByMcv", query = "SELECT b FROM BloodTest b WHERE b.mcv = :mcv"),
   @NamedQuery(name = "BloodTest.findByPatient", query = "SELECT b FROM BloodTest b WHERE b.patientId.patientId = :patientId ORDER BY b.examDate"),
   @NamedQuery(name = "BloodTest.findByMedicId", query = "SELECT b FROM BloodTest b WHERE b.medicId.id = :medicId ORDER BY b.examDate"),
   @NamedQuery(name = "BloodTest.findByOrderId", query = "SELECT b FROM BloodTest b WHERE b.labTestOrderId.id = :orderId ORDER BY b.examDate")
   
})
public class BloodTest implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "id")
   private Integer id;
   @Column(name = "exam_date")
   @Temporal(TemporalType.TIMESTAMP)
   private Date examDate;
   @Column(name = "hemoglobine")
   private Integer hemoglobine;
   @Column(name = "erytrocyt")
   private Integer erytrocyt;
   @Column(name = "mcv")
   private Integer mcv;
   @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
   @ManyToOne(optional = false)
   private Patient patientId;
   @JoinColumn(name = "lab_test_order_id", referencedColumnName = "id")
   @ManyToOne
   private LabTestOrder labTestOrderId;
   @JoinColumn(name = "medic_id", referencedColumnName = "id")
   @ManyToOne(optional = false)
   private Medic medicId;

   public BloodTest() {
   }

   public BloodTest(Integer id) {
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

   public Integer getHemoglobine() {
      return hemoglobine;
   }

   public void setHemoglobine(Integer hemoglobine) {
      this.hemoglobine = hemoglobine;
   }

   public Integer getErytrocyt() {
      return erytrocyt;
   }

   public void setErytrocyt(Integer erytrocyt) {
      this.erytrocyt = erytrocyt;
   }

   public Integer getMcv() {
      return mcv;
   }

   public void setMcv(Integer mcv) {
      this.mcv = mcv;
   }

   public Patient getPatientId() {
      return patientId;
   }

   public void setPatientId(Patient patientId) {
      this.patientId = patientId;
   }

   public LabTestOrder getLabTestOrderId() {
      return labTestOrderId;
   }

   public void setLabTestOrderId(LabTestOrder labTestOrderId) {
      this.labTestOrderId = labTestOrderId;
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
      if (!(object instanceof BloodTest)) {
         return false;
      }
      BloodTest other = (BloodTest) object;
      if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "pl.kosla.refactor.model.BloodTest[ id=" + id + " ]";
   }
   
}
