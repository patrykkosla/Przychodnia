/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;

/**
 *
 * @author patryk
 */
@Named(value = "makeAppoitmentBean")
@ViewScoped
public class MakeAppoitmentBean  implements Serializable{

    /**
     * Creates a new instance of MakeAppoitmentBean
     */
    public MakeAppoitmentBean() {
    }
    
    public String dayOfWeak(){  
        return null;
    }
    
    public Boolean checkIfDoctorAvaible(int medicId){
        return false;
    }
    
    
    
}
