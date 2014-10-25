/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;
import pl.kosla.przychodnia.controler.MedicController;
import pl.kosla.przychodnia.model.Medic;
import pl.kosla.przychodnia.model.SurgeryHasMedic;
/**
 *
 * @author patryk
 */
@Named(value = "surgeryDoctorBean")
@ConversationScoped
public class surgeryDoctorBean implements Serializable{
    
    private List<Medic> doctorsSurgeryList = null;
    private Medic selectedDoctor;
    
    @Inject private PatientBean pb;
    @Inject private MedicController mc;
     private SurgeryHasMedic shc;
    
   public boolean chechIfSurgerySelected(){
      if ( pb.getPatient().getSurgeryId().getId() != null ){
          return true;
             }
      else return false;
      }
       
      
   public void setMedicList(){
       int id =  pb.getPatient().getSurgeryId().getId();
      doctorsSurgeryList =  mc.getMedicsForSurgery(id);       
     //  doctorsSurgeryList =  mc.getMedicsForSurgeryP(pb.getPatient().getSurgeryId());       
   }

   public surgeryDoctorBean(){
   }
    @PostConstruct
    public void init() {  
      //  if (chechIfSurgerySelected())
        if ( pb.getPatient().getSurgeryId().getId() != null)
            setMedicList();
    }

    public List<Medic> getDoctorsSurgeryList() {
        return doctorsSurgeryList;
    }

    public void setDoctorsSurgeryList(List<Medic> doctorsSurgeryList) {
        this.doctorsSurgeryList = doctorsSurgeryList;
    }

    public Medic getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Medic selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }



    
    
}
