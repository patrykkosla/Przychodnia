/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import pl.kosla.przychodnia.model.Appoitment;

/**
 *
 * @author patryk
 */
@Stateless
public class BookingSortSessionBean {

   @Schedule(dayOfWeek = "Mon-Fri", month = "*", hour = "2", dayOfMonth = "*", year = "*", minute = "*", second = "0", persistent = false)
   
   public void myTimer() {
      System.out.println("Timer event: " + new Date());
   }
@EJB AppoitmentFacade appoitmentFacade;
    // Add business logic below. (Right-click in editor and choose
   // "Insert Code > Add Business Method")
   public void changeBookingStatus(){//zmiana status niedbytych wizyt
    List<Appoitment> al =  appoitmentFacade.getListofOldBookedAppp();
      for(Appoitment a : al){
         a.setStatus(Appoitment.PATIENTNOTSCHOUP);
      }
   }
}
