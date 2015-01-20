/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author patryk
 */
@Entity
@Table(name = "lab_test_order")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "LabTestOrder.findAll", query = "SELECT l FROM LabTestOrder l"),
   @NamedQuery(name = "LabTestOrder.findById", query = "SELECT l FROM LabTestOrder l WHERE l.id = :id"),
   @NamedQuery(name = "LabTestOrder.findByLabBlodOb", query = "SELECT l FROM LabTestOrder l WHERE l.labBlodOb = :labBlodOb"),
   @NamedQuery(name = "LabTestOrder.findByLabBlodMorfologia", query = "SELECT l FROM LabTestOrder l WHERE l.labBlodMorfologia = :labBlodMorfologia"),
   @NamedQuery(name = "LabTestOrder.findByLabBlodCells", query = "SELECT l FROM LabTestOrder l WHERE l.labBlodCells = :labBlodCells"),
   @NamedQuery(name = "LabTestOrder.findByAppoitmentId", query = "SELECT l FROM LabTestOrder l WHERE l.appoitmentId.id = :appId"),
   @NamedQuery(name = "LabTestOrder.findByLabFecesGeneralTest", query = "SELECT l FROM LabTestOrder l WHERE l.labFecesGeneralTest = :labFecesGeneralTest"),
   @NamedQuery(name = "LabTestOrder.findByLabUrineGeneralTest", query = "SELECT l FROM LabTestOrder l WHERE l.labUrineGeneralTest = :labUrineGeneralTest")})
public class LabTestOrder implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "id")
   private Integer id;
   @Column(name = "lab_blod_ob")
   private Boolean labBlodOb;
   @Column(name = "lab_blod_morfologia")
   private Boolean labBlodMorfologia;
   @Column(name = "lab_blod_cells")
   private Boolean labBlodCells;
   @Column(name = "lab_feces_general_test")
   private Boolean labFecesGeneralTest;
   @Column(name = "lab_urine_general_test")
   private Boolean labUrineGeneralTest;
   @OneToMany(mappedBy = "labTestOrderId")
   private Collection<BloodTest> bloodTestCollection;
   @JoinColumn(name = "appoitment_id", referencedColumnName = "id")
   @ManyToOne(optional = false)
   private Appoitment appoitmentId;
   
   public LabTestOrder() {
   }

   public LabTestOrder(Integer id) {
      this.id = id;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Boolean getLabBlodOb() {
      return labBlodOb;
   }

   public void setLabBlodOb(Boolean labBlodOb) {
      this.labBlodOb = labBlodOb;
   }

   public Boolean getLabBlodMorfologia() {
      return labBlodMorfologia;
   }

   public void setLabBlodMorfologia(Boolean labBlodMorfologia) {
      this.labBlodMorfologia = labBlodMorfologia;
   }

   public Boolean getLabBlodCells() {
      return labBlodCells;
   }

   public void setLabBlodCells(Boolean labBlodCells) {
      this.labBlodCells = labBlodCells;
   }

   public Boolean getLabFecesGeneralTest() {
      return labFecesGeneralTest;
   }

   public void setLabFecesGeneralTest(Boolean labFecesGeneralTest) {
      this.labFecesGeneralTest = labFecesGeneralTest;
   }

   public Boolean getLabUrineGeneralTest() {
      return labUrineGeneralTest;
   }

   public void setLabUrineGeneralTest(Boolean labUrineGeneralTest) {
      this.labUrineGeneralTest = labUrineGeneralTest;
   }

   @XmlTransient
   public Collection<BloodTest> getBloodTestCollection() {
      return bloodTestCollection;
   }

   public void setBloodTestCollection(Collection<BloodTest> bloodTestCollection) {
      this.bloodTestCollection = bloodTestCollection;
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
      if (!(object instanceof LabTestOrder)) {
         return false;
      }
      LabTestOrder other = (LabTestOrder) object;
      if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "pl.kosla.przychodnia.model.LabTestOrder[ id=" + id + " ]";
   }
   
}
