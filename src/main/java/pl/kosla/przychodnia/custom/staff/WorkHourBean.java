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
import pl.kosla.przychodnia.model.SurgeryHasMedic;
import pl.kosla.przychodnia.model.Workhour;
import pl.kosla.przychodnia.session.SurgeryHasMedicFacade;
import pl.kosla.przychodnia.session.WorkhourFacade;

/**
 *
 * @author patryk
 */
@Named(value = "workHourBean")
@ViewScoped
public class WorkHourBean implements Serializable{

@EJB WorkhourFacade workhourFacade;   
@EJB SurgeryHasMedicFacade SurgeryHasMedicFacade;

@Inject StaffBean staffBean;
private List<Workhour> workHourList;
private List<SurgeryHasMedic> surgeryHasMedicDoctorList;

   public WorkHourBean() {
   }
   
       @PostConstruct
   private void init() {  
      if(staffBean.getMedic() != null && staffBean.getMedic().getId() != null ){
         setWorkHourList(workhourFacade.getWorkhourForMedicId(staffBean.getMedic().getId()));
         
         
      }
      
      
   }

   public List<Workhour> getWorkHourList() {
      return workhourFacade.getWorkhourForMedicId(staffBean.getMedic().getId());
     // return workHourList;
   }

   public void setWorkHourList(List<Workhour> workHourList) {
      this.workHourList = workHourList;
   }

   public List<SurgeryHasMedic> getSurgeryHasMedicDoctorList() {
//      List<SurgeryHasMedic> temp = SurgeryHasMedicFacade.finaByMedicIsActive(staffBean.getMedic().getId(), true);
//      
//      for(SurgeryHasMedic s : temp ){
//        if( !s.getWorkhourCollection().isEmpty() ){
//           temp.remove(s);
//        }
//      }
      
      //return surgeryHasMedicDoctorList;
      return SurgeryHasMedicFacade.finaByMedicIsActiveEmpty(staffBean.getMedic().getId(), true);
      //return temp;
   }

   public void setSurgeryHasMedicDoctorList(List<SurgeryHasMedic> surgeryHasMedicDoctorList) {
      this.surgeryHasMedicDoctorList = surgeryHasMedicDoctorList;
   }


}
