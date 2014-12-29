/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.kosla.przychodnia.model.Patient;

/**
 *
 * @author patryk
 */
@Named(value = "surgeryPatiensController")
@ViewScoped
public class SurgeryPatiensController {
@Inject private StaffBean staffBean;


   /**
    * Creates a new instance of SurgeryPatiensControler
    */
   public SurgeryPatiensController() {
   }
   
   private List<Patient> patientsList;
   private Patient selectedPatient;
   
   @PostConstruct
    private void init() {  
      if (staffBean.getMedic()!= null){
         
      }
      
   }
   
   
}
