/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.patient;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pl.kosla.przychodnia.model.Patient;

/**
 *
 * @author patryk
 */
@Named(value = "patientSearchBean")
@RequestScoped
public class PatientSearchBean {

   public PatientSearchBean() {
   }
   private Patient patient;
   
   public void findPatientbyId(int patientId){
      
   }
   public void findPatientbyPesel(){
      
   }
   
}
