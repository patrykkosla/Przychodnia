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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Medic.findAll", query = "SELECT m FROM Medic m"),
    @NamedQuery(name = "Medic.findById", query = "SELECT m FROM Medic m WHERE m.id = :id"),
    @NamedQuery(name = "Medic.findByUsername", query = "SELECT m FROM Medic m WHERE m.username = :username"),
    @NamedQuery(name = "Medic.findByFirstName", query = "SELECT m FROM Medic m WHERE m.firstName = :firstName"),
    @NamedQuery(name = "Medic.findByLastName", query = "SELECT m FROM Medic m WHERE m.lastName = :lastName"),
    @NamedQuery(name = "Medic.findByPassword", query = "SELECT m FROM Medic m WHERE m.password = :password"),
    @NamedQuery(name = "Medic.findByPesel", query = "SELECT m FROM Medic m WHERE m.pesel = :pesel"),
    @NamedQuery(name = "Medic.findBySex", query = "SELECT m FROM Medic m WHERE m.sex = :sex"),
    @NamedQuery(name = "Medic.findByEnable", query = "SELECT m FROM Medic m WHERE m.enable = :enable"),
    @NamedQuery(name = "Medic.findByBornDate", query = "SELECT m FROM Medic m WHERE m.bornDate = :bornDate"),
    @NamedQuery(name = "Medic.findByCreateTime", query = "SELECT m FROM Medic m WHERE m.createTime = :createTime"),
    @NamedQuery(name = "Medic.findByType", query = "SELECT m FROM Medic m WHERE m.type = :type")})
public class Medic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 255)
    private String password;
    @Size(max = 12)
    private String pesel;
    private Boolean sex;
    private Boolean enable;
    @Column(name = "born_date")
    @Temporal(TemporalType.DATE)
    private Date bornDate;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Size(max = 9)
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicId")
    private Collection<Appoitment> appoitmentCollection;
    @JoinColumn(name = "addres_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Addres addresId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicId")
    private Collection<Perscripion> perscripionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicId")
    private Collection<BloodTest> bloodTestCollection;
    @OneToMany(mappedBy = "medicId")
    private Collection<Patient> patientCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicId")
    private Collection<Radiologia> radiologiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicId")
    private Collection<SurgeryHasMedic> surgeryHasMedicCollection;

    public Medic() {
    }

    public Medic(Integer id) {
        this.id = id;
    }

    public Medic(Integer id, String username, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Appoitment> getAppoitmentCollection() {
        return appoitmentCollection;
    }

    public void setAppoitmentCollection(Collection<Appoitment> appoitmentCollection) {
        this.appoitmentCollection = appoitmentCollection;
    }

    public Addres getAddresId() {
        return addresId;
    }

    public void setAddresId(Addres addresId) {
        this.addresId = addresId;
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
    public Collection<Patient> getPatientCollection() {
        return patientCollection;
    }

    public void setPatientCollection(Collection<Patient> patientCollection) {
        this.patientCollection = patientCollection;
    }

    @XmlTransient
    public Collection<Radiologia> getRadiologiaCollection() {
        return radiologiaCollection;
    }

    public void setRadiologiaCollection(Collection<Radiologia> radiologiaCollection) {
        this.radiologiaCollection = radiologiaCollection;
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
        if (!(object instanceof Medic)) {
            return false;
        }
        Medic other = (Medic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Medic[ id=" + id + " ]";
    }
    
}
