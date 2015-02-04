/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.kosla.przychodnia.model.BloodTest;
import pl.kosla.przychodnia.model.LabTestOrder;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.session.BloodTestFacade;
import pl.kosla.przychodnia.session.LabTestOrderFacade;
import pl.kosla.przychodnia.utilis.FacesUtils;

/**
 *
 * @author patryk
 */
@Named(value = "labolatoryOEBean")
@ViewScoped
public class LabolatoryOEBean  implements Serializable{

    @EJB LabTestOrderFacade labTestOrderFacade;
    @EJB BloodTestFacade bloodTestFacade;
   @Inject StaffBean staffBean; 
    
   private LabTestOrder labTestOrder;
   private BloodTest bloodTest;
   private List<BloodTest> bloodTestList;
   private Patient patient;
   private Boolean isNew;
   private BloodTest tempTest;
   
   public LabolatoryOEBean() {
   }
    @PostConstruct
   private void init() {  
      if( FacesUtils.getFromSession("SelectedLabOrder") != null){
         setLabTestOrder( (LabTestOrder) FacesUtils.getFromSession("SelectedLabOrder"));
         
        bloodTestList = bloodTestFacade.findBloodTestByOrderId(getLabTestOrder().getId());
          if(!bloodTestList.isEmpty() ){
             setBloodTest(bloodTestList.get(0));
          }
          else {
             tempTest = new BloodTest();
             if (staffBean.getMedic() != null && staffBean.getMedic().getId() != null){
                tempTest.setMedicId(staffBean.getMedic());
                tempTest.setExamDate(new Date());
                tempTest.setPatientId(getLabTestOrder().getAppoitmentId().getPatientId());
                tempTest.setLabTestOrderId(getLabTestOrder());
             }
             
            // bloodTest = new BloodTest();
             setIsNew(true);
          }
      }
   }
   
   public LabTestOrder getLabTestOrder() {
      return labTestOrder;
   }

   public void setLabTestOrder(LabTestOrder labTestOrder) {
      this.labTestOrder = labTestOrder;
   }

   public BloodTest getBloodTest() {
      return bloodTest;
   }

   public void setBloodTest(BloodTest bloodTest) {
      this.bloodTest = bloodTest;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public List<BloodTest> getBloodTestList() {
      return bloodTestList;
   }

   public void setBloodTestList(List<BloodTest> bloodTestList) {
      this.bloodTestList = bloodTestList;
   }

   public Boolean getIsNew() {
      return isNew;
   }

   public void setIsNew(Boolean isNew) {
      this.isNew = isNew;
   }

   public BloodTest getTempTest() {
      return tempTest;
   }

   public void setTempTest(BloodTest tempTest) {
      this.tempTest = tempTest;
   }
   
   
   
   
}
