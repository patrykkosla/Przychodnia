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
import pl.kosla.przychodnia.controler.AppoitmentController;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.SickLeave;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import static org.apache.commons.lang3.RandomStringUtils.random;
import org.joda.time.DateMidnight;
import pl.kosla.przychodnia.controler.LabTestOrderController;
import pl.kosla.przychodnia.controler.MedicineController;
import pl.kosla.przychodnia.enums.MedicType;
import pl.kosla.przychodnia.model.Diagnose;
import pl.kosla.przychodnia.model.LabTestOrder;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Perscripion;
import pl.kosla.przychodnia.model.RadiologyExamOrder;
import pl.kosla.przychodnia.session.DiagnoseFacade;
import pl.kosla.przychodnia.session.PerscripionFacade;
import pl.kosla.przychodnia.session.RadiologyExamOrderFacade;
import pl.kosla.przychodnia.session.SickLeaveFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.addToSession;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;
import static pl.kosla.przychodnia.utilis.FacesUtils.removeFromSession;


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
    @EJB private DiagnoseFacade diagnoseFacade;
    @EJB private RadiologyExamOrderFacade radiologyExamOrderFacade ;
 
    
    private List<SickLeave> sickLeaveItems;
    private SickLeave sickLeaveSelected;
    private List<Perscripion> perscripionItems;
    private Perscripion perscripionSelected;
    private Appoitment curentAppoitment; 
    private List<Diagnose> diagnoseList;
    private List<RadiologyExamOrder> radiologyExamOrderList;
    
   private Boolean docViewMode = false;
   public AppoitmentBean() {
   }
   @PostConstruct
   private void init() {  
       System.out.println("appoitmentBean: init start zaulek"+ getFromSession("patient"));
      if(sf.getMedic() != null && sf.getMedic().getId() != null){//check if medic
            if( getFromSession("CurentApp") != null ){
               curentAppoitment =  (Appoitment) getFromSession("CurentApp");
               Date ds = new Date();
               DateMidnight d = new DateMidnight(ds);
               if(curentAppoitment.getStatus().equals(Appoitment.REZERWACJA)){//Appoitment z rezerwacji
                  curentAppoitment.setAppoitmentDate(new Date());
                  docViewMode = true;
               }
               else if (sf.getMedic().getId().equals(curentAppoitment.getMedicId().getId()) && d.isEqual(new DateMidnight(curentAppoitment.getAppoitmentDate())) ){
                  docViewMode = true;
               }else if (sf.getMedic().getId().equals(curentAppoitment.getMedicId().getId()) ){
                  docViewMode = false;
                  
               }
               else{
                  docViewMode = false;
               }
               System.out.println("appoitmentBean z sesji");
               curentAppoitment.setStatus(Appoitment.PAST);
               
               
               
               
               
               
               
               
               
               
               
            }else if(getFromSession("newappoitment").equals(true) && getFromSession("curentPatient") != null && sf.getMedic().getType().equals(MedicType.DOCTOR)  ){//nowa wizyta
               System.out.println("faza 2");
                  
              // if( ((Appoitment)  getFromSession("CurentApp")).getPatientId().equals( (Patient) getFromSession("curentPatient")) )   {
               //   System.out.println("appoitmentBean kontynuacja");
                  
              // }else{
                  curentAppoitment = new Appoitment();
                  curentAppoitment.setMedicId(sf.getMedic());
                  curentAppoitment.setPatientId( (Patient) getFromSession("curentPatient") );
                  curentAppoitment.setAppoitmentDate(new Date());
                  appoitmentFacade.create(curentAppoitment);
                  addToSession("CurentApp", curentAppoitment);
                  addToSession("newappoitment", false);
                  System.out.println("appoitmentBean nowe");
                  curentAppoitment.setStatus(Appoitment.PAST);
                  docViewMode = true;
              // }
            }      
      }else if(getFromSession("patient") != null && getFromSession("CurentApp") != null){
          System.out.println("appoitmentBean: faza 3");
         if( ((Appoitment)getFromSession("CurentApp")).getPatientId().getPatientId().equals(   ((int)getFromSession("patient"))  )   ){
             System.out.println("appoitmentBean: old app for patient");
            curentAppoitment =  (Appoitment) getFromSession("CurentApp");
            docViewMode = false;
         }
         else{
             System.out.println("appoitmentBean: brak app w sesj");
         }
      }
   }
   
   public String FinischCurentAppoitment(){
      
      removeFromSession("CurentApp");
      return "/staff/doctor.xhtml?faces-redirect=true";
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
      return  sickLeaveFacade.getSickLeavesLitByAppoitmentId(curentAppoitment.getId());
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
   // return (List<Diagnose>) curentAppoitment.getDiagnoseCollection();
      return diagnoseFacade.getDiagnosesListFoArappoitmentId(curentAppoitment.getId());
   }

   public void setDiagnoseList(List<Diagnose> diagnoseList) {
      this.diagnoseList = diagnoseList;
   }

   public List<RadiologyExamOrder> getRadiologyExamOrderList() {
      return radiologyExamOrderFacade.getRadiologyExamOrderAppList(curentAppoitment.getId());
   }

   public void setRadiologyExamOrderList(List<RadiologyExamOrder> radiologyExamOrderList) {
      this.radiologyExamOrderList = radiologyExamOrderList;
   }

   public Boolean getDocViewMode() {
      return docViewMode;
   }




}
