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
import pl.kosla.przychodnia.controler.AppoitmentController;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import pl.kosla.przychodnia.session.PatientFacade;
import pl.kosla.przychodnia.session.SurgeryHasMedicFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.addToSession;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;

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
    @EJB private SurgeryHasMedicFacade surgeryHasMedicFacade;
    @EJB private PatientFacade patientFacade;
    @EJB private AppoitmentFacade appoitmentFacade;
   public DoctorBean() {
    }
   @PostConstruct
    private void init() {  
      if(sf.getMedic() != null){
        curentSurgery = (Surgery) getFromSession("curentSurgery");
        surgeryList = surgeryHasMedicFacade.findAllSurgeryFormMedicStatus(sf.getMedic().getId(), true);
        if(curentSurgery != null){
           //pobrać listę pacjentów
           //patiensList = patientFacade.getPatiensForDoctro(sf.getMedic().getId(), curentSurgery.getId(), true);
           //pobrać listę pacjentów którzy mają wizytę na dzisiaj
           appoitmentList = appoitmentFacade.allDocAppForSingelDay(sf.getMedic().getId(), curentSurgery.getId(), new Date(), Appoitment.REZERWACJA );
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
      return appoitmentList;
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
