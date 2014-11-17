/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.Medic;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;
import pl.kosla.przychodnia.session.AppoitmentFacade;
/**
 *
 * @author patryk
 */
@Named(value = "homePatientBean")
@RequestScoped
public class HomePatientBean implements Serializable{
    private static final long serialVersionUID = 1L;
    @Inject private PatientBean pb;
    @EJB
    private AppoitmentFacade appoitmentFacade;
    
    private Patient p;
    private Surgery selectedSurgery;
    private List<Appoitment> lastAppoitmentsList;
    private Appoitment selectedAppoitment;
    private Appoitment curentAppoitment;
    

    @PostConstruct
    private void init() {  
    }
  
//    public boolean checkIfIsSurgery(){       
//        Surgery s = pb.getPatient().getPatientSurgery();
//        return (s == null);
//    }
    public void setSurgeryForPatient(Surgery surgery ){
        pb.setSurgeryForPatient(surgery);
    }
    public void setDoctorForPatient(Medic medic){
        pb.setDoctorForPatient(medic);
        // dodać anulację wizyt
    }
    public void upDateData(){
        pb.upDatePatient();  
    }
    public boolean prepareEditPersonalData(){
        String temp = pb.getPatient().getBlogGrup();
        if(temp != null){
            pb.setRhTypeTemp(temp.substring(temp.length()-1));
            pb.setBlodGrupTemp(temp.substring(0, temp.length()-1));
            return true;
        }
        else{
            return false;
        }
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

    public List<Appoitment> getLastAppoitmentsList() {
        //return lastAppoitmentsList;
        //pb.getPatient().getAppoitmentCollection();
       return lastAppoitmentsList = appoitmentFacade.getLastAppointmentsForPatient("pas", pb.getPatient().getPatientId(), 5);
    }

    public void setLastAppoitmentsList(List<Appoitment> lastAppoitmentsList) {
        this.lastAppoitmentsList = lastAppoitmentsList;
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
    
}
