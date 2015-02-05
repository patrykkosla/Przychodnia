/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import static pl.kosla.przychodnia.utilis.FacesUtils.addToSession;

/**
 *
 * @author patryk
 */
@Named(value = "patientAppoitmens")
@ViewScoped
public class PatientAppoitmens  implements Serializable{
 private static final long serialVersionUID = 1L;
    @Inject private PatientBean pb;
    @EJB private AppoitmentFacade appoitmentFacade;

    public PatientAppoitmens() {}
    
    private List<Appoitment> appoitmentsList;
    private Appoitment selectedAppoitment;
    private Appoitment curentAppoitment;

       @PostConstruct
    private void init() { 
        appoitmentsList = appoitmentFacade.getAppointmentForPatient("pas", pb.getPatient().getPatientId());
    }
    public String  prepareAppoitemtView(Appoitment a){
       addToSession("CurentApp", selectedAppoitment);
       return "/staff/appoitment.xhtml?faces-redirect=true";
    }
    public AppoitmentFacade getAppoitmentFacade() {
        return appoitmentFacade;
    }

    public void setAppoitmentFacade(AppoitmentFacade appoitmentFacade) {
        this.appoitmentFacade = appoitmentFacade;
    }

    public Appoitment getSelectedAppoitment() {
        return selectedAppoitment;
    }

    public void setSelectedAppoitment(Appoitment selectedAppoitment) {
        this.selectedAppoitment = selectedAppoitment;
    }

    public Appoitment getCurentAppoitment() {
        return curentAppoitment;
    }

    public void setCurentAppoitment(Appoitment curentAppoitment) {
        this.curentAppoitment = curentAppoitment;
    }

    public List<Appoitment> getAppoitmentsList() {
        return appoitmentsList;
    }

    public void setAppoitmentsList(List<Appoitment> appoitmentsList) {
        this.appoitmentsList = appoitmentsList;
    }
    
    
}
