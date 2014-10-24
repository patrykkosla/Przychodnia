/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kosla.przychodnia.controler.SurgeryController;

/**
 *
 * @author patryk
 */
@Named("surgeryCustomController")
@ViewScoped
public class SurgeryCustomController{
 
    @Inject private SurgeryController surgerController;

    public SurgeryController getSurgerController() {
        return surgerController;
    }

    public void setSurgerController(SurgeryController surgerController) {
        this.surgerController = surgerController;
    }
    
    
    
    
    
    
}
