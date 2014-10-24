/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Surgery.findAll", query = "SELECT s FROM Surgery s"),
    @NamedQuery(name = "Surgery.findById", query = "SELECT s FROM Surgery s WHERE s.id = :id"),
    @NamedQuery(name = "Surgery.findByName", query = "SELECT s FROM Surgery s WHERE s.name = :name"),
    @NamedQuery(name = "Surgery.findByEmail", query = "SELECT s FROM Surgery s WHERE s.email = :email")})
public class Surgery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 220)
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 220)
    private String email;
    @OneToMany(mappedBy = "surgeryId")
    private Collection<Patient> patientCollection;
    @JoinColumn(name = "addres_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Addres addresId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surgeryId")
    private Collection<SurgeryHasMedic> surgeryHasMedicCollection;

    public Surgery() {
    }

    public Surgery(Integer id) {
        this.id = id;
    }

    public Surgery(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Patient> getPatientCollection() {
        return patientCollection;
    }

    public void setPatientCollection(Collection<Patient> patientCollection) {
        this.patientCollection = patientCollection;
    }

    public Addres getAddresId() {
        return addresId;
    }

    public void setAddresId(Addres addresId) {
        this.addresId = addresId;
    }

    @XmlTransient
    public Collection<SurgeryHasMedic> getSurgeryHasMedicCollection() {
        return surgeryHasMedicCollection;
    }

    public void setSurgeryHasMedicCollection(Collection<SurgeryHasMedic> surgeryHasMedicCollection) {
        this.surgeryHasMedicCollection = surgeryHasMedicCollection;
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
        if (!(object instanceof Surgery)) {
            return false;
        }
        Surgery other = (Surgery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Surgery[ id=" + id + " ]";
    }
    
}
