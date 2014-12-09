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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByPatientId", query = "SELECT p FROM Patient p WHERE p.patientId = :patientId"),
    @NamedQuery(name = "Patient.findByUsername", query = "SELECT p FROM Patient p WHERE p.username = :username"),
    @NamedQuery(name = "Patient.findByFirstName", query = "SELECT p FROM Patient p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Patient.findByLastName", query = "SELECT p FROM Patient p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Patient.findByPassword", query = "SELECT p FROM Patient p WHERE p.password = :password"),
    @NamedQuery(name = "Patient.findByPesel", query = "SELECT p FROM Patient p WHERE p.pesel = :pesel"),
    @NamedQuery(name = "Patient.findBySex", query = "SELECT p FROM Patient p WHERE p.sex = :sex"),
    @NamedQuery(name = "Patient.findByEnable", query = "SELECT p FROM Patient p WHERE p.enable = :enable"),
    @NamedQuery(name = "Patient.findByBornDate", query = "SELECT p FROM Patient p WHERE p.bornDate = :bornDate"),
    @NamedQuery(name = "Patient.findByCreateTime", query = "SELECT p FROM Patient p WHERE p.createTime = :createTime"),
    @NamedQuery(name = "Patient.findByBlogGrup", query = "SELECT p FROM Patient p WHERE p.blogGrup = :blogGrup"),
    @NamedQuery(name = "Patient.findByWeitht", query = "SELECT p FROM Patient p WHERE p.weitht = :weitht"),
    @NamedQuery(name = "Patient.findByHeight", query = "SELECT p FROM Patient p WHERE p.height = :height"),
    @NamedQuery(name = "Patient.login", query = "SELECT p FROM Patient p WHERE p.username = :username AND p.password = :password")})

public class Patient extends Persone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patient_id")
    private Integer patientId;
    @Size(max = 3)
    @Column(name = "blog_grup")
    private String blogGrup;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float weitht;
    private Integer height;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Collection<Appoitment> appoitmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Collection<Perscripion> perscripionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Collection<BloodTest> bloodTestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientFk")
    private Collection<SugerTest> sugerTestCollection;
    @JoinColumn(name = "addres_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Addres addresId;
    
    @JoinColumn(name = "surgery_id", referencedColumnName = "id")
    @ManyToOne
    private Surgery surgeryId;
    
    @JoinColumn(name = "medic_id", referencedColumnName = "id")
    @ManyToOne
    private Medic medicId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Collection<Radiologia> radiologiaCollection;

    public Patient() {
    }

    public Patient(Integer patientId) {
        this.patientId = patientId;
    }

    public Patient(Integer patientId, String username, String firstName, String lastName) {
        this.patientId = patientId;
        super.username = username;
        super.firstName = firstName;
        super.lastName = lastName;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getBlogGrup() {
        return blogGrup;
    }

    public void setBlogGrup(String blogGrup) {
        this.blogGrup = blogGrup;
    }

    public Float getWeitht() {
        return weitht;
    }

    public void setWeitht(Float weitht) {
        this.weitht = weitht;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @XmlTransient
    public Collection<Appoitment> getAppoitmentCollection() {
        return appoitmentCollection;
    }

    public void setAppoitmentCollection(Collection<Appoitment> appoitmentCollection) {
        this.appoitmentCollection = appoitmentCollection;
    }

    @XmlTransient
    public Collection<Perscripion> getPerscripionCollection() {
        return perscripionCollection;
    }

    public void setPerscripionCollection(Collection<Perscripion> perscripionCollection) {
        this.perscripionCollection = perscripionCollection;
    }

    @XmlTransient
    public Collection<BloodTest> getBloodTestCollection() {
        return bloodTestCollection;
    }

    public void setBloodTestCollection(Collection<BloodTest> bloodTestCollection) {
        this.bloodTestCollection = bloodTestCollection;
    }

    @XmlTransient
    public Collection<SugerTest> getSugerTestCollection() {
        return sugerTestCollection;
    }

    public void setSugerTestCollection(Collection<SugerTest> sugerTestCollection) {
        this.sugerTestCollection = sugerTestCollection;
    }

    public Addres getAddresId() {
        return addresId;
    }

    public void setAddresId(Addres addresId) {
        this.addresId = addresId;
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

    @XmlTransient
    public Collection<Radiologia> getRadiologiaCollection() {
        return radiologiaCollection;
    }

    public void setRadiologiaCollection(Collection<Radiologia> radiologiaCollection) {
        this.radiologiaCollection = radiologiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientId != null ? patientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.patientId == null && other.patientId != null) || (this.patientId != null && !this.patientId.equals(other.patientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Patient[ patientId=" + patientId + " ]";
    }
    
}
