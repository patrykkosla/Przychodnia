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
import pl.kosla.przychodnia.controler.AppoitmentController;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.SickLeave;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import static org.apache.commons.lang3.RandomStringUtils.random;
import pl.kosla.przychodnia.controler.LabTestOrderController;
import pl.kosla.przychodnia.controler.MedicineController;
import pl.kosla.przychodnia.enums.MedicType;
import pl.kosla.przychodnia.model.Diagnose;
import pl.kosla.przychodnia.model.LabTestOrder;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Perscripion;
import pl.kosla.przychodnia.session.PerscripionFacade;
import pl.kosla.przychodnia.session.SickLeaveFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;


/**
 *
 * @author patryk
 */
@Named(value = "appoitmentBean")
@ViewScoped
public class AppoitmentBean implements Serializable {
    @Inject private StaffBean sf;
    @Inject private AppoitmentController apc;
    @Inject private MedicineController medicineController;
    @Inject private LabTestOrderController labTestOrderController;
    
    @EJB private AppoitmentFacade appoitmentFacade;
    @EJB private SickLeaveFacade sickLeaveFacade;
    @EJB private PerscripionFacade perscripionFacade;
 
    
    private List<SickLeave> sickLeaveItems = null;
    private SickLeave sickLeaveSelected;
    private List<Perscripion> perscripionItems;
    private Perscripion perscripionSelected;
    private Appoitment curentAppoitment; 
    private List<Diagnose> diagnoseList;
   
   public AppoitmentBean() {
   }
   @PostConstruct
   private void init() {  
      if(sf.getMedic() != null){ //&& sf.getMedic().getType().equals(MedicType.DOCTOR)
            if( getFromSession("CurentApp") != null ){
               curentAppoitment =  (Appoitment) getFromSession("CurentApp");
               //sprawdzanie czy wizyta z rezerwacji czy przegląd lub edycja 
              // sickLeaveItems = (List<SickLeave>) curentAppoitment.getSickLeaveCollection();
               System.out.println("appoitmentBean z sesji");
               
            }else if(getFromSession("newappoitment").equals(true) && getFromSession("curentPatient") != null && sf.getMedic().getType().equals(MedicType.DOCTOR) ){//nowa wizyta
                  System.out.println("faza 2");
               curentAppoitment = new Appoitment();
               curentAppoitment.setMedicId(sf.getMedic());
               curentAppoitment.setPatientId( (Patient) getFromSession("curentPatient") );
               appoitmentFacade.create(curentAppoitment);
               //apc.setSelected(curentAppoitment);
               //apc.create();
               //curentAppoitment = apc.getSelected();
               System.out.println("appoitmentBean nowe");
                  
            }
      }

   }
   public void refresch(){
      sickLeaveItems = (List<SickLeave>) curentAppoitment.getSickLeaveCollection();
      perscripionItems = (List) curentAppoitment.getPerscripionCollection();
   }
   public SickLeave prepareCreateSickLeave() {
      sickLeaveSelected = new SickLeave();
      return sickLeaveSelected;
   }
   public void createSickLeave() {
      sickLeaveSelected.setSecureCode(random(9));
      sickLeaveSelected.setAppoitmentId(curentAppoitment);
      sickLeaveFacade.create(sickLeaveSelected);
   }
   public Perscripion prepareCreatePerscripion() {
        perscripionSelected = new Perscripion();
        return perscripionSelected;
   }
   public void createPerscripion() {
      perscripionSelected.setAppoitmentId(curentAppoitment);
      perscripionFacade.create(perscripionSelected);
   }
   public String addNote(){
      apc.setSelected(curentAppoitment);
      apc.create();
      return "/staff/patients.xhtml?faces-redirect=true";
   }
   
   
   public Appoitment getCurentAppoitment() {
      return curentAppoitment;
   }

   public void setCurentAppoitment(Appoitment curentAppoitment) {
      this.curentAppoitment = curentAppoitment;
   }
   protected void initializeEmbeddableKey() {
   } 

   public List<SickLeave> getSickLeaveItems() {
      return sickLeaveItems;
   }

   public void setSickLeaveItems(List<SickLeave> sickLeaveItems) {
      this.sickLeaveItems = sickLeaveItems;
   }

   public SickLeave getSickLeaveSelected() {
      return sickLeaveSelected;
   }

   public void setSickLeaveSelected(SickLeave sickLeaveSelected) {
      this.sickLeaveSelected = sickLeaveSelected;
   }

   public List<Perscripion> getPerscripionItems() {
      return  perscripionFacade.findPerscripionForApp(curentAppoitment.getId());
   }

   public void setPerscripionItems(List<Perscripion> perscripionItems) {
      this.perscripionItems = perscripionItems;
   }

   public Perscripion getPerscripionSelected() {
      return perscripionSelected;
   }

   public void setPerscripionSelected(Perscripion perscripionSelected) {
      this.perscripionSelected = perscripionSelected;
   }

   public MedicineController getMedicineController() {
      return medicineController;
   }

   public void setMedicineController(MedicineController medicineController) {
      this.medicineController = medicineController;
   }
   public List<LabTestOrder> getLabExamList(){
    return  labTestOrderController.getOrdersAppList(curentAppoitment.getId());

   }

   public List<Diagnose> getDiagnoseList() {
    return (List<Diagnose>) curentAppoitment.getDiagnoseCollection();
   }

   public void setDiagnoseList(List<Diagnose> diagnoseList) {
      this.diagnoseList = diagnoseList;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
