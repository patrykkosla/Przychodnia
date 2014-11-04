/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import pl.kosla.przychodnia.controler.util.JsfUtil;
import pl.kosla.przychodnia.model.Addres;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.session.AddresFacade;
import pl.kosla.przychodnia.session.PatientFacade;
import pl.kosla.przychodnia.utilis.JSFUtils;
import pl.kosla.przychodnia.utilis.SessionUtil;

/**
 *
 * @author patryk
 */
@Named(value = "patientRejestracjaBean")
@RequestScoped
public class PatientRejestracjaBean implements Serializable{

    /**
     * Creates a new instance of PatientRejestracjaBean
     */
    public PatientRejestracjaBean() {
      patient = new Patient();
      addres = new Addres();
    }
    
    @EJB
    private pl.kosla.przychodnia.session.AddresFacade addresFacade;
    @EJB 
    private pl.kosla.przychodnia.session.PatientFacade patientFacade; 
    
    private static final long serialVersionUID = 1L;
    
    private Patient patient;
    private Addres addres;
    
    private String blodGrupTemp;
    private String rhTypeTemp;
    
    
    
    public String doRegister(){
        boolean check = false;
        
        if(blodGrupTemp != null && rhTypeTemp != null){
            patient.setBlogGrup(blodGrupTemp + rhTypeTemp);
        }
        
        Calendar calendar = Calendar.getInstance();
        Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());    
        patient.setCreateTime(currentTimestamp);       
        try
        {  
            addresFacade.create(addres);
            patient.setAddresId(addres);
            patientFacade.create(patient);
            
            JsfUtil.addSuccessMessage(addres.getId().toString()); 
            JSFUtils.addInfoMsg("sucess_messages","User Registered successfully");
            check = true;
            
        } catch (EJBException ex)
        {   
             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
             JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
        if(check)
        {
             //   setSessione();
             //   return "index.xhtml?faces-redirect=true";
        }
        return null;
    }
    private void setSessione(){
        patient = patientFacade.getPatienByUsername(patient);
        // get Http Session and store username
        HttpSession session = SessionUtil.getSession();
        session.setAttribute("username", patient.getUsername());

    }    

    public AddresFacade getAddresFacade() {
        return addresFacade;
    }

    public void setAddresFacade(AddresFacade addresFacade) {
        this.addresFacade = addresFacade;
    }

    public PatientFacade getPatientFacade() {
        return patientFacade;
    }

    public void setPatientFacade(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Addres getAddres() {
        return addres;
    }

    public void setAddres(Addres addres) {
        this.addres = addres;
    }

    public String getBlodGrupTemp() {
        return blodGrupTemp;
    }

    public void setBlodGrupTemp(String blodGrupTemp) {
        this.blodGrupTemp = blodGrupTemp;
    }

    public String getRhTypeTemp() {
        return rhTypeTemp;
    }

    public void setRhTypeTemp(String rhTypeTemp) {
        this.rhTypeTemp = rhTypeTemp;
    }
    
}
