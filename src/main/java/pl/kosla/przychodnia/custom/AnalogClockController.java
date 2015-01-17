/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author patryk
 */
@Named(value = "analogClockController")
@RequestScoped
public class AnalogClockController implements Serializable {

   /**
    * Creates a new instance of AnalogClockController
    */
   public AnalogClockController() {}
   
   private final Date now = new Date();  
  
   public Date getNow() {  
        return now;  
    }  
      
   
}
