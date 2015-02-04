/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import pl.kosla.przychodnia.controler.AppoitmentController;
import pl.kosla.przychodnia.controler.WorkhourController;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import pl.kosla.przychodnia.session.PatientFacade;
import pl.kosla.przychodnia.session.SurgeryHasMedicFacade;
import pl.kosla.przychodnia.session.WorkhourFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.addCookie;
import static pl.kosla.przychodnia.utilis.FacesUtils.addToSession;
import static pl.kosla.przychodnia.utilis.FacesUtils.getCookie;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;
import static pl.kosla.przychodnia.utilis.FacesUtils.removeFromSession;

/**
 *
 * @author patryk
 */
@Named(value = "doctorBean")
@ViewScoped
public class DoctorBean implements Serializable {
 private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of DoctorBean
     */

    @Inject private StaffBean sf;
    @Inject private AppoitmentController apc;
    @Inject private WorkhourController workhourController;
    @EJB private SurgeryHasMedicFacade surgeryHasMedicFacade;
    @EJB private PatientFacade patientFacade;
    @EJB private AppoitmentFacade appoitmentFacade;
    @EJB private WorkhourFacade workhourFacade;
 
   public DoctorBean() {
    }
   @PostConstruct
    private void init() {  
      if(sf.getMedic() != null){
         surgeryList = surgeryHasMedicFacade.findAllSurgeryFormMedicStatus(sf.getMedic().getId(), true);
         
         
         
         Cookie cookie = getCookie("curentSurgery");
        if(cookie != null && cookie.getValue() != null){
           for(Surgery s: surgeryList){
              if( (s.getId().compareTo(Integer.parseInt( cookie.getValue()))) == 0){
                 curentSurgery = s;
                 addToSession("curentSurgery", curentSurgery);
                 System.out.println("doctorBean: z ciastka");
              }
           }  
        }
         
         curentSurgery = (Surgery) getFromSession("curentSurgery");
         if(curentSurgery != null){
            System.out.println("doctorBean : z sesj");
         }
        
        

        if(curentSurgery != null){
           //pobrać listę pacjentów
           //patiensList = patientFacade.getPatiensForDoctro(sf.getMedic().getId(), curentSurgery.getId(), true);
           //pobrać listę pacjentów którzy mają wizytę na dzisiaj
           appoitmentList = appoitmentFacade.allDocAppForSingelDay(sf.getMedic().getId(), curentSurgery.getId(), new Date(), Appoitment.REZERWACJA );
           
           workhourController.setSelected( workhourFacade.getWorkhourforMedicinSurgery(sf.getMedic().getId(), curentSurgery.getId()));
        }
        
      }
      
      
    }
        
    private List<Surgery> surgeryList;
    private Surgery selectedSurgery;  
    private Surgery curentSurgery;
    
    private List<Patient> patiensList;
    private Patient selectedPatient;
    private Patient curentPatient;
    
    private List<Appoitment> appoitmentList;
    private Appoitment selextedAppoitment;
    private Appoitment curentAppoitment;
    
    
   public void prepateAppView(Appoitment a){
       addToSession("CurentAppId", a);
    }
    
 
   public void prepatePatientView(Patient patient){
      
   }
   
   public String changeCurentSurgery(){
         
      System.out.println(selectedSurgery.toString());
      removeFromSession("curentSurgery");
      addToSession("curentSurgery", selectedSurgery);
      addCookie("curentSurgery", selectedSurgery.getId().toString());
      return "";
    
   } 
   public String prepatePatientAppoitment(Appoitment appoitment){
      //removeFromSession("CurentApp");
      
      addToSession("curentPatient", appoitment.getPatientId());
      addToSession("CurentApp", appoitment);
      addToSession("newappoitment", false);
      return "/staff/appoitment.xhtml?faces-redirect=true";
   } 
  
    public void getBookedAppointment(int daysFuther){
       appoitmentList =  apc.getBookedAppointment(daysFuther, sf.getMedic().getId());
    }
    
    public void getAllOldAppointments(){
        sf.getMedic().getAppoitmentCollection();
        
    }
    public void getAllAppointments(String status){
        
    }
    public void getPatientsList(){
        sf.getMedic().getAppoitmentCollection();
    }

   public List<Surgery> getSurgeryList() {
      return surgeryList;
   }

   public void setSurgeryList(List<Surgery> surgeryList) {
      this.surgeryList = surgeryList;
   }

   public Surgery getSelectedSurgery() {
      return selectedSurgery;
   }

   public void setSelectedSurgery(Surgery selectedSurgery) {
      this.selectedSurgery = selectedSurgery;
   }

   public Surgery getCurentSurgery() {
      return curentSurgery;
   }

   public void setCurentSurgery(Surgery curentSurgery) {
      this.curentSurgery = curentSurgery;
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

   public List<Appoitment> getAppoitmentList() {
      return appoitmentFacade.allDocAppForSingelDay(sf.getMedic().getId(), curentSurgery.getId(), new Date(), Appoitment.REZERWACJA );
      //return appoitmentList;
   }

   public void setAppoitmentList(List<Appoitment> appoitmentList) {
      this.appoitmentList = appoitmentList;
   }

   public Appoitment getSelextedAppoitment() {
      return selextedAppoitment;
   }

   public void setSelextedAppoitment(Appoitment selextedAppoitment) {
      this.selextedAppoitment = selextedAppoitment;
   }

   public Appoitment getCurentAppoitment() {
      return curentAppoitment;
   }

   public void setCurentAppoitment(Appoitment curentAppoitment) {
      this.curentAppoitment = curentAppoitment;
   }
    
}
