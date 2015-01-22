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
    @NamedQuery(name = "Sicknessdic.findAll", query = "SELECT s FROM Sicknessdic s"),
    @NamedQuery(name = "Sicknessdic.findById", query = "SELECT s FROM Sicknessdic s WHERE s.id = :id"),
    @NamedQuery(name = "Sicknessdic.findByName", query = "SELECT s FROM Sicknessdic s WHERE s.name = :name"),
    @NamedQuery(name = "Sicknessdic.findByLatinName", query = "SELECT s FROM Sicknessdic s WHERE s.latinName = :latinName"),
    @NamedQuery(name = "Sicknessdic.findByIcd10", query = "SELECT s FROM Sicknessdic s WHERE s.icd10 = :icd10")})
public class Sicknessdic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "latin_name")
    private String latinName;
    @Size(max = 12)
    private String icd10;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sicknessFk")
    private Collection<Diagnose> diagnoseCollection;

    public Sicknessdic() {
    }

    public Sicknessdic(Integer id) {
        this.id = id;
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

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getIcd10() {
        return icd10;
    }

    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }

    @XmlTransient
    public Collection<Diagnose> getDiagnoseCollection() {
        return diagnoseCollection;
    }

    public void setDiagnoseCollection(Collection<Diagnose> diagnoseCollection) {
        this.diagnoseCollection = diagnoseCollection;
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
        if (!(object instanceof Sicknessdic)) {
            return false;
        }
        Sicknessdic other = (Sicknessdic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.Sicknessdic[ id=" + id + " ]";
    }
    
}
