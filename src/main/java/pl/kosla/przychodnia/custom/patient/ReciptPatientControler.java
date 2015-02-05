/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.patient;

import java.util.List;
import javafx.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.kosla.przychodnia.custom.PatientBean;
import pl.kosla.przychodnia.model.Perscripion;
import pl.kosla.przychodnia.session.PerscripionFacade;

/**
 *
 * @author patryk
 */
@Named(value = "reciptPatientControler")
@ViewScoped
public class ReciptPatientControler {
   @Inject private PatientBean pb;
   
   @EJB private PerscripionFacade perscripionFacade;
   
   private List<Perscripion> perscripionList;
   private Perscripion selected;
   
   @PostConstruct
    private void init() {  
      if (pb.getPatient() != null){
         setperscripionList();
      }
    }
   private void setperscripionList(){      
     // perscripionList  = (List<Perscripion>) pb.getPatient().getPerscripionCollection();
   }
   /**
    * Creates a new instance of ReciptPatientControler
    */
   public ReciptPatientControler() {
   }
   public void prepateView(ActionEvent actionEvent){
      
      
   }
   public List<Perscripion> getPerscripionList() {
      return perscripionFacade.findPerscripionForPatient(pb.getPatient().getPatientId());
     // return perscripionList;
   }

   public void setPerscripionList(List<Perscripion> perscripionList) {
      this.perscripionList = perscripionList;
   }

   public Perscripion getSelected() {
      return selected;
   }

   public void setSelected(Perscripion selected) {
      this.selected = selected;
   }

}
