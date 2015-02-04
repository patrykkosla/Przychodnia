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
import pl.kosla.przychodnia.model.Workhour;
import pl.kosla.przychodnia.session.WorkhourFacade;

/**
 *
 * @author patryk
 */
@Named(value = "workHourBean")
@ViewScoped
public class WorkHourBean implements Serializable{

@EJB WorkhourFacade workhourFacade;   
@Inject StaffBean staffBean;
private List<Workhour> workHourList;

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
   
}
