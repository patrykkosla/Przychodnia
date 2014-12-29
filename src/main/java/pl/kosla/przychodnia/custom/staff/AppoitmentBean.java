/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import pl.kosla.przychodnia.controler.AppoitmentController;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.getFromSession;

/**
 *
 * @author patryk
 */
@Named(value = "appoitmentBean")
@Dependent
public class AppoitmentBean {
    @Inject private StaffBean sf;
    @Inject private AppoitmentController apc;
    @EJB private AppoitmentFacade appoitmentFacade;
   /**
    * Creates a new instance of AppoitmentBean
    */
   private Appoitment curentAppoitment; 
   public AppoitmentBean() {
   }
   @PostConstruct
   private void init() {  
      if(sf.getMedic() != null && getFromSession("CurentAppId") != null ){
         curentAppoitment = appoitmentFacade.findById( (Integer) getFromSession("CurentAppId") );
      }
   }

   public Appoitment getCurentAppoitment() {
      return curentAppoitment;
   }

   public void setCurentAppoitment(Appoitment curentAppoitment) {
      this.curentAppoitment = curentAppoitment;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
