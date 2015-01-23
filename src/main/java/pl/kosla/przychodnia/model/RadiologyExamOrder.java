/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pl.kosla.przychodnia.enums.RadiologyExamPart;

/**
 *
 * @author patryk
 */
@Entity
@Table(name = "radiology_exam_order")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "RadiologyExamOrder.findAll", query = "SELECT r FROM RadiologyExamOrder r"),
   @NamedQuery(name = "RadiologyExamOrder.findById", query = "SELECT r FROM RadiologyExamOrder r WHERE r.id = :id"),
   @NamedQuery(name = "RadiologyExamOrder.findByAppoitmentId", query = "SELECT r FROM RadiologyExamOrder r WHERE r.appoitmentId.id = :appoitmentId"),
   @NamedQuery(name = "RadiologyExamOrder.findByPatientId", query = "SELECT r FROM RadiologyExamOrder r WHERE r.appoitmentId.patientId.patientId = :patientId"),
   @NamedQuery(name = "RadiologyExamOrder.findByRadiologyExamPart", query = "SELECT r FROM RadiologyExamOrder r WHERE r.radiologyExamPart = :radiologyExamPart"),
   @NamedQuery(name = "RadiologyExamOrder.findByRadiologyExamType", query = "SELECT r FROM RadiologyExamOrder r WHERE r.radiologyExamType = :radiologyExamType")})
public class RadiologyExamOrder implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "id")
   private Integer id;
   @Enumerated(EnumType.STRING)
   @Column(name = "radiology_exam_part")
   private RadiologyExamPart radiologyExamPart;
   @Size(max = 45)
   @Column(name = "radiology_exam_type")
   private String radiologyExamType;
   @OneToMany(mappedBy = "examOrderId")
   private Collection<Radiologia> radiologiaCollection;
   @JoinColumn(name = "appoitment_id", referencedColumnName = "id")
   @ManyToOne(optional = false)
   private Appoitment appoitmentId;
   
   public RadiologyExamOrder() {
   }

   public RadiologyExamOrder(Integer id) {
      this.id = id;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public RadiologyExamPart getRadiologyExamPart() {
      return radiologyExamPart;
   }

   public void setRadiologyExamPart(RadiologyExamPart radiologyExamPart) {
      this.radiologyExamPart = radiologyExamPart;
   }

   public String getRadiologyExamType() {
      return radiologyExamType;
   }

   public void setRadiologyExamType(String radiologyExamType) {
      this.radiologyExamType = radiologyExamType;
   }

   @XmlTransient
   public Collection<Radiologia> getRadiologiaCollection() {
      return radiologiaCollection;
   }

   public void setRadiologiaCollection(Collection<Radiologia> radiologiaCollection) {
      this.radiologiaCollection = radiologiaCollection;
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
      if (!(object instanceof RadiologyExamOrder)) {
         return false;
      }
      RadiologyExamOrder other = (RadiologyExamOrder) object;
      if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "pl.kosla.przychodnia.model.RadiologyExamOrder[ id=" + id + " ]";
   }
   
}
