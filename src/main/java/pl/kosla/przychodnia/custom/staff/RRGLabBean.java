/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import pl.kosla.przychodnia.model.LabTestOrder;
import pl.kosla.przychodnia.model.RadiologyExamOrder;
import pl.kosla.przychodnia.session.BloodTestFacade;
import pl.kosla.przychodnia.session.LabTestOrderFacade;
import pl.kosla.przychodnia.session.RadiologiaFacade;
import pl.kosla.przychodnia.session.RadiologyExamOrderFacade;
import pl.kosla.przychodnia.utilis.FacesUtils;

/**
 *
 * @author patryk
 */
@Named(value = "rRGLabBean")
@ViewScoped
public class RRGLabBean implements Serializable{
   
    @Inject private StaffBean sf;
    
    @EJB RadiologyExamOrderFacade radiologyExamOrderFacade;
    @EJB RadiologiaFacade radiologiaFacade;
    
    private int orderId;
    private RadiologyExamOrder radiologyExamOrder;
    
   @PostConstruct
   private void init() {  

      
   }
   /**
    * Creates a new instance of RRGLabBean
    */
   public RRGLabBean() {
   }
   public String proceedByOrderId(){
      setRadiologyExamOrder( radiologyExamOrderFacade.findRTGOrdeById(orderId) );
      if(getRadiologyExamOrder() != null){
         FacesUtils.addToSession("SelectedRTGOrder", getRadiologyExamOrder());
      return "/RTGExame.xhtml?faces-redirect=true";
      }
      return null;
   }
   
   public int getOrderId() {
      return orderId;
   }

   public void setOrderId(int orderId) {
      this.orderId = orderId;
   }

   public RadiologyExamOrder getRadiologyExamOrder() {
      return radiologyExamOrder;
   }

   public void setRadiologyExamOrder(RadiologyExamOrder radiologyExamOrder) {
      this.radiologyExamOrder = radiologyExamOrder;
   }

}
