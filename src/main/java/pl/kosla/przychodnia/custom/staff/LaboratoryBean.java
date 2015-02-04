/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
@Named(value = "laboratoryBean")
@ViewScoped
public class LaboratoryBean implements Serializable{
   
    @Inject private StaffBean sf;
    
    @EJB LabTestOrderFacade labTestOrderFacade;
    @EJB BloodTestFacade bloodTestFacade;

   private LabTestOrder labTestOrder;
   private List<LabTestOrder> labTestOrderList;
   private BloodTest bloodTest;
   private List<BloodTest> bloodTestList;
   private Patient patient;

   private int orderId;
   
   public LaboratoryBean() {
   }
   @PostConstruct
   private void init() {  
      if( FacesUtils.getFromSession("SelectedLabOrder") != null){
         setLabTestOrder( (LabTestOrder) FacesUtils.getFromSession("SelectedLabOrder"));
      }
      
   }
   
   public void findLabTestOrder(){
      setLabTestOrder( labTestOrderFacade.findLabOrdeById(labTestOrder.getId()) );
   }
   public List<BloodTest> findLabTestByOrderId(int orderId ){
      return  bloodTestFacade.findBloodTestByOrderId(orderId);
   }
   
   public LabTestOrder getLabTestOrder() {
      return labTestOrder;
   }
   public void orderIdICoDalej(){
      int orderId = labTestOrder.getId();
      List<BloodTest> bl = findLabTestByOrderId( orderId );
      if (bl.isEmpty()){// nie ma jeszcze badań do danege zlecenia
         
      }
      
      
   }
   public void checkOrderId(ActionEvent actionEvent){
   }
   public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
   public String proceedByOrderId(){
      setLabTestOrder( labTestOrderFacade.findLabOrdeById(orderId) );
      if(getLabTestOrder() != null){
               FacesUtils.addToSession("SelectedLabOrder", getLabTestOrder());
      return "/laboratoryOrderExame.xhtml?faces-redirect=true";

      }
      return null;
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

   public StaffBean getSf() {
      return sf;
   }

   public void setSf(StaffBean sf) {
      this.sf = sf;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public List<LabTestOrder> getLabTestOrderList() {
      if(sf.getMedic() != null && sf.getMedic().getId() != null ){
      return labTestOrderList = labTestOrderFacade.findLabOrdeByMedicId(sf.getMedic().getId());
      }
      return labTestOrderList;
   }

   public void setLabTestOrderList(List<LabTestOrder> labTestOrderList) {
      this.labTestOrderList = labTestOrderList;
   }

   public List<BloodTest> getBloodTestList() {
      if(sf.getMedic() != null && sf.getMedic().getId() != null ){
         return bloodTestFacade.findBloodTestByMedicId(sf.getMedic().getId());
      }
      return bloodTestList;
   }

   public void setBloodTestList(List<BloodTest> bloodTestList) {
      this.bloodTestList = bloodTestList;
   }

   public int getOrderId() {
      return orderId;
   }

   public void setOrderId(int orderId) {
      this.orderId = orderId;
   }

  
   
}
