/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;
import pl.kosla.przychodnia.session.PatientFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.addToSession;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;
import static pl.kosla.przychodnia.utilis.FacesUtils.removeFromSession;
import pl.kosla.przychodnia.utilis.SessionUtil;

/**
 *
 * @author patryk
 */
@Named(value = "patientsListBean")
@ViewScoped
public class PatientsListBean implements Serializable{

   /**
    * Creates a new instance of PatientsListBean
    */
   public PatientsListBean() {
   }
    private List<Patient> patiensList;
    private Patient selectedPatient;
    private Patient curentPatient;
    @Inject private StaffBean sf;
    @EJB private PatientFacade patientFacade;
  @PostConstruct
    private void init() {  
//      HttpSession session = SessionUtil.getSession();
//      session.getAttribute(null)
       
      Surgery curentSurgery = (Surgery) getFromSession("curentSurgery");
      if(curentSurgery != null && curentSurgery.getId() != null){
         patiensList = patientFacade.getPatiensForDoctro(sf.getMedic().getId(), curentSurgery.getId(), true);
      }else{
         System.out.println("patientsListBean: brak listy");
      }
     
    }
   private void setPatientsList(){
      
      
   } 
   public List<Patient> getPatiensList() {
      return patiensList;
   }

   public void setPatiensList(List<Patient> patiensList) {
      this.patiensList = patiensList;
   }

   public Patient getSelectedPatient() {
      return selectedPatient;
   }

   public void setSelectedPatient(Patient selectedPatient) {
      this.selectedPatient = selectedPatient;
   }

   public Patient getCurentPatient() {
      return curentPatient;
   }

   public void setCurentPatient(Patient curentPatient) {
      this.curentPatient = curentPatient;
   }
   public String prepatePatientView(Patient patient){
      HttpSession session = SessionUtil.getSession();
      session.setAttribute("curentPatient", patient);
      return "/staff/karta.xhtml?faces-redirect=true";
   } 
   public String prepatePatientAppoitment(Patient patient){
      addToSession("curentPatient", patient);
      if(getFromSession("CurentApp") != null ){
         if( ((Appoitment) getFromSession("CurentApp")).getPatientId().equals( (Patient) getFromSession("curentPatient")) ) {
            return "/staff/appoitment.xhtml?faces-redirect=true";
            
            
         }
      }
      removeFromSession("CurentApp");
      addToSession("newappoitment", true);
      return "/staff/appoitment.xhtml?faces-redirect=true";
   } 
    
    
}
