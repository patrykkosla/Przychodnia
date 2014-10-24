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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author patryk
 */
@Entity
@Table(name = "suger_test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SugerTest.findAll", query = "SELECT s FROM SugerTest s"),
    @NamedQuery(name = "SugerTest.findById", query = "SELECT s FROM SugerTest s WHERE s.id = :id"),
    @NamedQuery(name = "SugerTest.findBySugerLevel", query = "SELECT s FROM SugerTest s WHERE s.sugerLevel = :sugerLevel"),
    @NamedQuery(name = "SugerTest.findByAfterMeal", query = "SELECT s FROM SugerTest s WHERE s.afterMeal = :afterMeal"),
    @NamedQuery(name = "SugerTest.findByTestDateTmie", query = "SELECT s FROM SugerTest s WHERE s.testDateTmie = :testDateTmie")})
public class SugerTest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "suger_level")
    private int sugerLevel;
    @Column(name = "after_meal")
    private Boolean afterMeal;
    @Column(name = "test_date_tmie")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testDateTmie;
    @JoinColumn(name = "patient_fk", referencedColumnName = "patient_id")
    @ManyToOne(optional = false)
    private Patient patientFk;

    public SugerTest() {
    }

    public SugerTest(Integer id) {
        this.id = id;
    }

    public SugerTest(Integer id, int sugerLevel) {
        this.id = id;
        this.sugerLevel = sugerLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSugerLevel() {
        return sugerLevel;
    }

    public void setSugerLevel(int sugerLevel) {
        this.sugerLevel = sugerLevel;
    }

    public Boolean getAfterMeal() {
        return afterMeal;
    }

    public void setAfterMeal(Boolean afterMeal) {
        this.afterMeal = afterMeal;
    }

    public Date getTestDateTmie() {
        return testDateTmie;
    }

    public void setTestDateTmie(Date testDateTmie) {
        this.testDateTmie = testDateTmie;
    }

    public Patient getPatientFk() {
        return patientFk;
    }

    public void setPatientFk(Patient patientFk) {
        this.patientFk = patientFk;
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
        if (!(object instanceof SugerTest)) {
            return false;
        }
        SugerTest other = (SugerTest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kosla.przychodnia.model.SugerTest[ id=" + id + " ]";
    }
    
}
