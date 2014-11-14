/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import pl.kosla.przychodnia.controler.AppoitmentController;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.Medic;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.model.Surgery;

/**
 *
 * @author patryk
 */
@Named(value = "doctorBean")
@SessionScoped
public class DoctorBean implements Serializable {
 private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of DoctorBean
     */
    public DoctorBean() {
    }
    @Inject private StaffBean sf;
    @Inject private AppoitmentController apc;
        
    private List<Surgery> surgeryList;
    private Surgery selectedSurgery;  
    private Surgery curentSurgery;
    
    private List<Patient> patiensList;
    private Patient selectedPatient;
    private Patient curentPatient;
    
    private List<Appoitment> appoitmentList;
    private Appoitment selextedAppoitment;
    private Appoitment curentAppoitment;
    
    
    public void getBookedAppointment(int daysFuther){
       appoitmentList =  apc.getBookedAppointment(daysFuther, sf.getMedic().getId());
    }
    
    public void getAllOldAppointments(){
        sf.getMedic().getAppoitmentCollection();
        
    }
    public void getAllAppointments(String status){
        
    }
    public void getPatientsList(){
        sf.getMedic().getAppoitmentCollection();
    }
    
}
