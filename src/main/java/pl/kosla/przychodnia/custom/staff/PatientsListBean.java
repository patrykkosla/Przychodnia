/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;
import pl.kosla.przychodnia.session.PatientFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;
import pl.kosla.przychodnia.utilis.SessionUtil;

/**
 *
 * @author patryk
 */
@Named(value = "patientsListBean")
@ViewScoped
public class PatientsListBean {

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
      patiensList = patientFacade.getPatiensForDoctro(sf.getMedic().getId(), curentSurgery.getId(), true);
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
      session.setAttribute("curentPatient", patient.getPatientId());
      
      
      return "/staff/patientview.xhrml";
   } 
    
    
}
