/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;
/**
 *
 * @author patryk
 */
@Named(value = "homePatientBean")
@RequestScoped
public class HomePatientBean implements Serializable{
    private static final long serialVersionUID = 1L;
    @Inject private PatientBean pb;
    
    private Patient p;
    private Surgery selectedSurgery;
    

    @PostConstruct
    private void init() {  
    }
  
//    public boolean checkIfIsSurgery(){       
//        Surgery s = pb.getPatient().getPatientSurgery();
//        return (s == null);
//    }
    public void setSurgeryForPatient(ActionEvent event){
        

    }
    
    public Patient getP() {
        return p;
    }

    public void setP(Patient p) {
        this.p = p;
    }

    public PatientBean getPb() {
        return pb;
    }

    public void setPb(PatientBean pb) {
        this.pb = pb;
    }

    public Surgery getSelectedSurgery() {
        return selectedSurgery;
    }

    public void setSelectedSurgery(Surgery selectedSurgery) {
        this.selectedSurgery = selectedSurgery;
    }
    
}
