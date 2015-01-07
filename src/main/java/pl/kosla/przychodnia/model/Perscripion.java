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
    @NamedQuery(name = "Perscripion.findAll", query = "SELECT p FROM Perscripion p"),
    @NamedQuery(name = "Perscripion.findById", query = "SELECT p FROM Perscripion p WHERE p.id = :id"),
    @NamedQuery(name = "Perscripion.findByDose", query = "SELECT p FROM Perscripion p WHERE p.dose = :dose"),
    @NamedQuery(name = "Perscripion.findByPosteData", query = "SELECT p FROM Perscripion p WHERE p.posteData = :posteData")})
public class Perscripion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 220)
    private String dose;
    @Column(name = "poste_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date posteData;
    @JoinColumn(name = "medicine_fk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medicine medicineFk;
    @JoinColumn(name = "appoitment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Appoitment appoitmentId;

    public Perscripion() {
    }

    public Perscripion(Integer id) {
        this.id = id;
    }

    public Perscripion(Integer id, String dose) {
        this.id = id;
        this.dose = dose;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Date getPosteData() {
        return posteData;
    }

    public void setPosteData(Date posteData) {
        this.posteData = posteData;
    }

    public Medicine getMedicineFk() {
        return medicineFk;
    }

    public void setMedicineFk(Medicine medicineFk) {
        this.medicineFk = medicineFk;
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
        if (!(object instanceof Perscripion)) {
            return false;
        }
        Perscripion other = (Perscripion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Perscripion[ id=" + id + " ]";
    }
    
}
