/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.Patient;

/**
 *
 * @author patryk
 */
@Stateless
public class PatientFacade extends AbstractFacade<Patient> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientFacade() {
        super(Patient.class);
    }
    public boolean authenticate(Patient patient) {
//      //za przykładaem z zajęć  
       TypedQuery<Patient> query = getEntityManager().createNamedQuery("Patient.login",Patient.class);
       // Query query = getEntityManager().createNamedQuery("Patient.login",Patient.class);  
        query.setParameter("username", patient.getUsername());
        query.setParameter("password", patient.getPassword());
//        Przykład z zajęc powoduje wyjatki przy braku wyniku
//        if (query.getSingleResult() == null){
//                return false;   
//        }
//        else {        
//            return true;
//        }      
        //Użyciee listy nie powoduje wyjątków
        List<Patient> listaPacjentow = query.getResultList();
        if(listaPacjentow.isEmpty()){
            return false;
        }
        return true;       
    }
    public boolean secureAuthenticate(Patient patient) {
//      //za przykładaem z zajęć  
       TypedQuery<Patient> query = getEntityManager().createNamedQuery("Patient.login",Patient.class);
       // Query query = getEntityManager().createNamedQuery("Patient.login",Patient.class);  
        query.setParameter("username", patient.getUsername());
        query.setParameter("password", patient.getPassword());
//        Przykład z zajęc powoduje wyjatki przy braku wyniku
//        if (query.getSingleResult() == null){
//                return false;   
//        }
//        else {        
//            return true;
//        }      
        //Użyciee listy nie powoduje wyjątków
        List<Patient> listaPacjentow = query.getResultList();
        if(listaPacjentow.isEmpty()){
            return false;
        }
        return true;       
    }
    
    public Patient getPatienByUsername(Patient patient){
       // Patient.findByUsernamenie
        TypedQuery<Patient> q = em.createNamedQuery("Patient.findByUsername", Patient.class);
        q.setParameter("username", patient.getUsername() );
       // q.setMaxResults(1);
        return q.getSingleResult();
}
        
}