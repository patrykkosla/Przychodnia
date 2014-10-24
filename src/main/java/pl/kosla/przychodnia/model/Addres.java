/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Addres.findAll", query = "SELECT a FROM Addres a"),
    @NamedQuery(name = "Addres.findById", query = "SELECT a FROM Addres a WHERE a.id = :id"),
    @NamedQuery(name = "Addres.findByPlace", query = "SELECT a FROM Addres a WHERE a.place = :place"),
    @NamedQuery(name = "Addres.findByStreet", query = "SELECT a FROM Addres a WHERE a.street = :street"),
    @NamedQuery(name = "Addres.findByPosteCode", query = "SELECT a FROM Addres a WHERE a.posteCode = :posteCode"),
    @NamedQuery(name = "Addres.findByPhoneNumber", query = "SELECT a FROM Addres a WHERE a.phoneNumber = :phoneNumber")})
public class Addres implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 45)
    private String place;
    @Size(max = 45)
    private String street;
    @Size(max = 8)
    @Column(name = "poste_code")
    private String posteCode;
    @Size(max = 12)
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addresId")
    private Collection<Medic> medicCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addresId")
    private Collection<Patient> patientCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addresId")
    private Collection<Surgery> surgeryCollection;

    public Addres() {
    }

    public Addres(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPosteCode() {
        return posteCode;
    }

    public void setPosteCode(String posteCode) {
        this.posteCode = posteCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlTransient
    public Collection<Medic> getMedicCollection() {
        return medicCollection;
    }

    public void setMedicCollection(Collection<Medic> medicCollection) {
        this.medicCollection = medicCollection;
    }

    @XmlTransient
    public Collection<Patient> getPatientCollection() {
        return patientCollection;
    }

    public void setPatientCollection(Collection<Patient> patientCollection) {
        this.patientCollection = patientCollection;
    }

    @XmlTransient
    public Collection<Surgery> getSurgeryCollection() {
        return surgeryCollection;
    }

    public void setSurgeryCollection(Collection<Surgery> surgeryCollection) {
        this.surgeryCollection = surgeryCollection;
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
        if (!(object instanceof Addres)) {
            return false;
        }
        Addres other = (Addres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Addres[ id=" + id + " ]";
    }
    
}
