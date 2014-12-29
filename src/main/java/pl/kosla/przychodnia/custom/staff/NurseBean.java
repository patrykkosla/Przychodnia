/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import pl.kosla.przychodnia.controler.PatientController;
import pl.kosla.przychodnia.controler.SurgeryController;
import pl.kosla.przychodnia.controler.SurgeryHasMedicController;
import pl.kosla.przychodnia.model.Medic;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;


/**
 * lek1@lek.com
 * @author patryk
 */
@Named(value = "nurseBean")
@SessionScoped 
public class NurseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of Nurse
     */
    public NurseBean() {
    }
    @Inject private PatientController pc;
    @Inject private SurgeryController sc;
    @Inject private StaffBean sf;
    @Inject private SurgeryHasMedicController sugeryMedic;
    
  
    
    private List<Surgery> surgeryList;
    private Surgery selectedSurgery;  
    private Surgery curentSurgery;
    private List<Medic> doctorsList;
    private Medic selectedDoctor;
    private Medic curentDoctor;
    private List<Patient> patiensList;
    private Patient selectedPatient;
    private Patient curentPatient;
    
    @PostConstruct
    private void init() {  
      if(sf.getMedic() != null &&  sf.getMedic().getType().equals("nurse") ){
         surgeryList =  getSurgeryList();
      }
      
    }

    public boolean checkSurgery(){
        if(getSurgeryList() == null )
            return false;
        else
            return true;
    }
    
    
    
    
    
    public void setSurgeryforView(Surgery s){
        curentSurgery = s;
       // doctorsList = getDoctorsForSurgery(curentSurgery);
        doctorsList = null;
        selectedDoctor = null;
        curentDoctor = null;
        
    }
    public void setDocftorforView(Medic doc){
        curentDoctor = doc;
        patiensList = getPatienttsForDoctor();
    }
     
    public List<Medic> getDoctorsForSurgery(Surgery s){
        return  sf. getMedicFacade().mediForSurgery(sf.getMedic().getId());
    } 
    public List<Patient> getPatienttsForDoctor(){
//        if(sc.getSelected() != null){
//            List<Patient> patLs = null;
//           for( Patient p : sc.getSelected().getPatientCollection() ){
//               if(p.getMedicId().getId() == selectedDoctor.getId() ){
//                   patLs.add(p);
//               }
//           }
//        }
//        return null;
        if(curentSurgery == null || curentDoctor == null){
          return null;
        }
        else{
            List<Patient> patients = null;
          for( Patient p :  curentDoctor.getPatientCollection() ){
              if(Objects.equals(p.getSurgeryId().getId(), curentSurgery.getId())){
                  patients.add(p);
                  
              }
          }
          return patients;
        }
        
    }
    public List<Patient> getAllPatientsForSurgery(Surgery s){
        sc.setSelected(s);
        List list;
        if(  sc.getSelected().getPatientCollection() instanceof List){
         list= (List)sc.getSelected().getPatientCollection() ;
        }
        else
          list = new ArrayList( sc.getSelected().getPatientCollection());   
        
        return list;
    }
    
    public void clearDoctorList(){
        doctorsList = null;
        selectedDoctor = null;
    }
    
    
/**
 * 
 * Getres i steres
 */
    public StaffBean getSf() {
        return sf;
    }

    public void setSf(StaffBean sf) {
        this.sf = sf;
    }

    
//    public List<Surgery> getSurgeryList() {
//        sf.getMedic().getId();
//        Collection<SurgeryHasMedic> lista = sf.getMedic().getSurgeryHasMedicCollection();
//        surgeryList = null;
//        for(SurgeryHasMedic i : lista){
//           if( i.getIsAtive() && i.getSecureLevel() >= 1 ){
//               surgeryList.add(i.getSurgeryId());
//           }
//        }        
//        return surgeryList;
//    }
    
    public void setActivePatient(){
        
    }
    public void setFastAppoitment(Patient p){
        // getDoctorFutherAppotment()
        
        //checkDoctorAccessibility()
        
        
    }
    
    
    
    public List<Surgery> getSurgeryList() {
         
        return sugeryMedic.findAllSurgeryForMedic( sf.getMedic());
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

    public List<Medic> getDoctorsList() {
        doctorsList = getDoctorsForSurgery(curentSurgery);
        return doctorsList;
    }

    public void setDoctorsList(List<Medic> doctorsList) {
        this.doctorsList = doctorsList;
    }

    public Medic getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Medic selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public List<Patient> getPatiensList() {
        if(curentDoctor == null && curentSurgery != null)
        {
           return getAllPatientsForSurgery(curentSurgery);
        }
        if(curentDoctor != null && curentSurgery != null) {   
        return getPatienttsForDoctor();
        }
        else
        return null;
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

    public Medic getCurentDoctor() {
        return curentDoctor;
    }

    public void setCurentDoctor(Medic curentDoctor) {
        this.curentDoctor = curentDoctor;
    }

    public Patient getCurentPatient() {
        return curentPatient;
    }

    public void setCurentPatient(Patient curentPatient) {
        this.curentPatient = curentPatient;
    }

    public PatientController getPc() {
        return pc;
    }

    public void setPc(PatientController pc) {
        this.pc = pc;
    }
 
    
    
    
}
