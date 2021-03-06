/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.Medic;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.addToSession;
/**
 *
 * @author patryk
 */
@Named(value = "homePatientBean")
@ViewScoped
public class HomePatientBean implements Serializable{
    private static final long serialVersionUID = 1L;
    @Inject private PatientBean pb;
    @EJB private AppoitmentFacade appoitmentFacade;
    
    private Patient p;
    private Surgery selectedSurgery;
    private List<Appoitment> lastAppoitmentsList;
    private Appoitment selectedAppoitment;
    private Appoitment curentAppoitment;
    private List<Appoitment> bookedAppoitmentsList;

    @PostConstruct
    private void init() {  
      fillBookedAppoitmentsList();
    }
  
   public void bookAppoitmentAction(ActionEvent actionEvent) {
      // check app amount
      RequestContext context = RequestContext.getCurrentInstance();
      boolean docSet = false; 
      if(pb.getPatient().getSurgeryId() != null){
         if(pb.getPatient().getMedicId() != null){
            //if(pb.getAppRezCount() > 3){
            //  docSet = false;
            //  addMessage("Możliwe są tylko 3 jednoczesne rezerwacje"); 
            //}
            //else{
               // tu aktywacje listy możliwych terminów
               docSet = true;
            //}
         }
         else{
            docSet = false;
            addMessage("Wybierz lekarza lub skontakuj się z przychodnią aby wybrać lekarze i umówić termin");
         }
      }else{ 
         docSet = false;
         addMessage("Wybierz Przychodnie i lekarza aby się zarejestrować na wizytę");
      }   
      context.addCallbackParam("docSet", docSet);   
   }   
   public void bookAppoitment(){
      
      
      
   }
   public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
   }

    
    
    
    public void setSurgeryForPatient(Surgery surgery ){
       cancellAllApp();
       pb.setSurgeryForPatient(surgery);
    }
    public void setDoctorForPatient(Medic medic){
       
      // dodać anulację wizyt
       cancellAllApp();
       
        pb.setDoctorForPatient(medic);
        
    }
    
    public void  cancellAllApp(){
            bookedAppoitmentsList = appoitmentFacade.getAppointmentForPatient(Appoitment.REZERWACJA, pb.getPatient().getPatientId());
         if(!bookedAppoitmentsList.isEmpty()){
            for(Appoitment a : bookedAppoitmentsList){
               a.setStatus(Appoitment.CANCELD);
            }
         }
   }
                
    public void upDateData(){
        pb.upDatePatient();  
    }
    public boolean prepareEditPersonalData(){
       
       if(pb.getPatient().getBlogGrup() != null && !pb.getPatient().getBlogGrup().isEmpty() ){
        String temp = pb.getPatient().getBlogGrup();
       // if(temp != null){
            pb.setRhTypeTemp(temp.substring(temp.length()-2));
            pb.setBlodGrupTemp(temp.substring(0, temp.length()-2));
            return true;
        }
        else{
            return false;
        }
    }
    public String  prepareAppoitemtView(Appoitment a){
       addToSession("CurentApp", selectedAppoitment);
       return "/staff/appoitment.xhtml?faces-redirect=true";
    }
    
    
    public Patient getP() {
        return p;
    }

    public void setP(Patient p) {
        this.p = p;
    }

    public PatientBean getPb() {
        return pb;
    }

    public void setPb(PatientBean pb) {
        this.pb = pb;
    }

    public Surgery getSelectedSurgery() {
        return selectedSurgery;
    }

    public void setSelectedSurgery(Surgery selectedSurgery) {
        this.selectedSurgery = selectedSurgery;
    }

    public List<Appoitment> getLastAppoitmentsList() {
        //return lastAppoitmentsList;
        //pb.getPatient().getAppoitmentCollection();
       return lastAppoitmentsList = appoitmentFacade.getLastAppointmentsForPatient(Appoitment.PAST, pb.getPatient().getPatientId(), 5);
    }

    public void setLastAppoitmentsList(List<Appoitment> lastAppoitmentsList) {
        this.lastAppoitmentsList = lastAppoitmentsList;
    }

    public Appoitment getSelectedAppoitment() {
        return selectedAppoitment;
    }

    public void setSelectedAppoitment(Appoitment selectedAppoitment) {
        this.selectedAppoitment = selectedAppoitment;
    }


    public Appoitment getCurentAppoitment() {
        return curentAppoitment;
    }

    public void setCurentAppoitment(Appoitment curentAppoitment) {
        this.curentAppoitment = curentAppoitment;
    }

   public List<Appoitment> getBookedAppoitmentsList() {
      return bookedAppoitmentsList;
   }

   public void setBookedAppoitmentsList(List<Appoitment> bookedAppoitmentsList) {
      this.bookedAppoitmentsList = bookedAppoitmentsList;
   }
   public void fillBookedAppoitmentsList() {
      this.bookedAppoitmentsList =appoitmentFacade.getAppointmentForPatient(Appoitment.REZERWACJA, pb.getPatient().getPatientId());
   }
    
}
