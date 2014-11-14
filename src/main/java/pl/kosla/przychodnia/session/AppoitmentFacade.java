/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.Calendar;
import static java.util.Calendar.DAY_OF_WEEK;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.Appoitment;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author patryk
 */
@Stateless
public class AppoitmentFacade extends AbstractFacade<Appoitment> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppoitmentFacade() {
        super(Appoitment.class);
    }
    public List<Appoitment> getBookedAppointment(int days, int medicId){
        
        Date currentDate = new Date();
        
        // Dodawanie czasu prze GregorianCalendar
        //Calendar calendar = new GregorianCalendar();
        //calendar.add(Calendar.DATE, days);
        //Date endDate = calendar.getTime();
        
        //Dodanie czasu z wykorzystaniem biblioteki org.apache.commons.
        Date endDate = DateUtils.addDays(currentDate, days);
     
        TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findFutherBooked", Appoitment.class);
        q.setParameter("status", "rez");
        q.setParameter("med", medicId);
        q.setParameter("startDate", currentDate, TemporalType.DATE);
        q.setParameter("endDate", endDate, TemporalType.DATE);
        
        return q.getResultList();
        
    }
    public List<Appoitment> getAppointmentForMedic(String stat, int medicId){
        TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findMedicStatus", Appoitment.class);
        q.setParameter("status", stat);
        q.setParameter("med", medicId);
        
        return q.getResultList();      
    }
    public List<Appoitment> getAppointmentForMedic(String stat, int medicId, int patientId){
        TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findMedicPatientStatus", Appoitment.class);
        q.setParameter("status", stat);
        q.setParameter("med", medicId);
        q.setParameter("patientId", patientId);
        
        return q.getResultList();      
    }
    
    
//        public Patient getPatienByUsername(Patient patient){
//       // Patient.findByUsernamenie
//        TypedQuery<Patient> q = em.createNamedQuery("Patient.findByUsername", Patient.class);
//        q.setParameter("username", patient.getUsername() );
//       // q.setMaxResults(1);
//        return q.getSingleResult();
//    }
}
