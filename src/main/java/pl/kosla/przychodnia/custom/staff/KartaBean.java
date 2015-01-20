/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.kosla.przychodnia.controler.LabTestOrderController;
import pl.kosla.przychodnia.controler.PatientController;
import pl.kosla.przychodnia.enums.MedicType;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.BloodTest;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Radiologia;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import pl.kosla.przychodnia.session.BloodTestFacade;
import pl.kosla.przychodnia.session.RadiologiaFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;

/**
 *
 * @author patryk
 */
@Named(value = "kartaBean")
@ViewScoped
public class KartaBean {

   @Inject private StaffBean sf;
   @Inject private PatientController patientController;
   @EJB private AppoitmentFacade appoitmentFacade;
   @EJB private BloodTestFacade bloodTestFacade;
   @EJB private RadiologiaFacade radiologiaFacade;
   private Patient p;
   private List<Appoitment> bookedAppoitmentsList;
   private List<Appoitment> appoitmentsList;
   private List<BloodTest> bloodTestList;
   private List<Radiologia> radiologiasList;
   
   /**
    * Creates a new instance of KartaBean
    */
   public KartaBean() {
   }
    @PostConstruct
   private void init() {  
   if( getFromSession("curentPatient") != null){
      patientController.setSelected( (Patient) getFromSession("curentPatient") );
      }

   }
   
   public void fillBookedAppoitmentsList() {
      this.bookedAppoitmentsList =appoitmentFacade.getAppointmentForPatient(Appoitment.REZERWACJA, patientController.getSelected().getPatientId());
   }

   public List<Appoitment> getBookedAppoitmentsList() {
      return bookedAppoitmentsList;
   }

   public void setBookedAppoitmentsList(List<Appoitment> bookedAppoitmentsList) {
      this.bookedAppoitmentsList = bookedAppoitmentsList;
   }

   public List<Appoitment> getAppoitmentsList() {
      return appoitmentsList;
   }

   public void setAppoitmentsList(List<Appoitment> appoitmentsList) {
      this.appoitmentsList = appoitmentsList;
   }

   public List<BloodTest> getBloodTestList() {
     return  bloodTestFacade.getPatintsResults(patientController.getSelected().getPatientId(), 5);
   }

   public void setBloodTestList(List<BloodTest> bloodTestList) {
      this.bloodTestList = bloodTestList;
   }

   public List<Radiologia> getRadiologiasList() {
      return radiologiaFacade.getPatintsResults(patientController.getSelected().getPatientId(), 5);
   }

   public void setRadiologiasList(List<Radiologia> radiologiasList) {
      this.radiologiasList = radiologiasList;
   }
   
   
   
}
