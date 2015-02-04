/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.kosla.przychodnia.controler.PatientController;
import pl.kosla.przychodnia.enums.MedicType;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.BloodTest;
import pl.kosla.przychodnia.model.Diagnose;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Perscripion;
import pl.kosla.przychodnia.model.Radiologia;
import pl.kosla.przychodnia.model.SickLeave;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import pl.kosla.przychodnia.session.BloodTestFacade;
import pl.kosla.przychodnia.session.DiagnoseFacade;
import pl.kosla.przychodnia.session.PerscripionFacade;
import pl.kosla.przychodnia.session.RadiologiaFacade;
import pl.kosla.przychodnia.session.SickLeaveFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;

/**
 *
 * @author patryk
 */
@Named(value = "kartaBean")
@ViewScoped
public class KartaBean implements Serializable{

   @Inject private StaffBean sf;
   @Inject private PatientController patientController;
   @EJB private AppoitmentFacade appoitmentFacade;
   @EJB private BloodTestFacade bloodTestFacade;
   @EJB private RadiologiaFacade radiologiaFacade;
   @EJB private PerscripionFacade perscripionFacade;
   @EJB private DiagnoseFacade diagnoseFacade;
   @EJB private SickLeaveFacade sickLeaveFacade;
   
   private Patient p;
   private List<Appoitment> bookedAppoitmentsList;
   private List<Appoitment> appoitmentsList;
   private List<BloodTest> bloodTestList;
   private List<Radiologia> radiologiasList;
   private List<Perscripion> perscripionsList;
   private List<Diagnose> diagnosesList;
   private List<SickLeave> sickLeaveList;
   
   private String role;
   /**
    * Creates a new instance of KartaBean
    */
   public KartaBean() {
   }
    @PostConstruct
   private void init() {  
      /*
      1 sprawdzic kto 
      
      
      */
      
   if( getFromSession("curentPatient") != null){
      System.out.println("kartaBean: jest pacjent");
      patientController.setSelected( (Patient) getFromSession("curentPatient") );
      if( getFromSession("role") != null){
            System.out.println("kartaBean: sprawdza uprawnienia");
            if(getFromSession("role").equals(MedicType.DOCTOR)){
               role = "doctor";
            }
            if(getFromSession("role").equals(MedicType.NURSE)){
               role = "nurse";
            }
      
      }else{
         role = "patient";
         
      }
      
      }
      System.out.println("kartaBean: brak pacjenta");
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
      return (List<Appoitment>) patientController.getSelected().getAppoitmentCollection();
     // return appoitmentsList;
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

   public PatientController getPatientController() {
      return patientController;
   }

   public void setPatientController(PatientController patientController) {
      this.patientController = patientController;
   }

   public List<Perscripion> getPerscripionsList() {
      return perscripionFacade.findPerscripionForPatient(patientController.getSelected().getPatientId(), 5);
   }
   public List<Perscripion> getPerscripionsListLong() {
      return perscripionFacade.findPerscripionForPatient(patientController.getSelected().getPatientId());
   }

   public void setPerscripionsList(List<Perscripion> perscripionsList) {
      this.perscripionsList = perscripionsList;
   }

   public List<Diagnose> getDiagnosesList() {
      return diagnosesList = diagnoseFacade.getDiagnosesListForPatient(patientController.getSelected().getPatientId(), 10);
   }

   public void setDiagnosesList(List<Diagnose> diagnosesList) {
      this.diagnosesList = diagnosesList;
   }

   public List<SickLeave> getSickLeaveList() {
      return sickLeaveList;
   }

   public void setSickLeaveList(List<SickLeave> sickLeaveList) {
      this.sickLeaveList = sickLeaveList;
   }
   public List<SickLeave> getSickLeaveListLong() {
     return sickLeaveFacade.getSickLeavesLitByPatientId(patientController.getSelected().getPatientId());
      //return sickLeaveList;
   }
   
   
   
}
