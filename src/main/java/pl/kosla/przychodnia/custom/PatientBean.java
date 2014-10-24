package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import pl.kosla.przychodnia.model.Addres;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import pl.kosla.przychodnia.controler.util.JsfUtil;
import pl.kosla.przychodnia.model.Patient;
import pl.kosla.przychodnia.session.AddresFacade;
import pl.kosla.przychodnia.session.PatientFacade;
import pl.kosla.przychodnia.utilis.JSFUtils;
import pl.kosla.przychodnia.utilis.SessionUtil;

@Named(value = "PatientBean")
@SessionScoped
/**
 *      
 * @author patryk
 */
public class PatientBean implements Serializable{
    
    @EJB
    private pl.kosla.przychodnia.session.AddresFacade addresFacade;
    @EJB 
    private pl.kosla.przychodnia.session.PatientFacade patientFacade;
    
    private static final long serialVersionUID = 1L;
    
    private Patient patient;
    private Addres addres;
    
    private String blodGrupTemp;
    private String rhTypeTemp;
    private String password;
    private String uname;
    
    //zapamiętywanie chasła
    boolean remember;
    String remember1 = "";
    
  // private static final Logger logger = Logger.getLogger(newPatient.class.getName());
    
    public String doRegister(){
        boolean check = false;
        
        if(blodGrupTemp != null && rhTypeTemp != null){
        patient.setBlogGrup(blodGrupTemp + rhTypeTemp);
        }
        
        Calendar calendar = Calendar.getInstance();
        Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());    
        patient.setCreateTime(currentTimestamp);
        
        JsfUtil.addSuccessMessage("akcja"); 
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
                setSessione();
                return "home.xhtml?faces-redirect=true";
        }
        return null;
    }
    private void setSessione(){
        patient = patientFacade.getPatienByUsername(patient);
        // get Http Session and store username
        HttpSession session = SessionUtil.getSession();
        session.setAttribute("username", patient.getUsername());

    }
    
    public void login(ActionEvent event) {//ActionEvent event
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false; 
        patient.setPassword(password);
        patient.setUsername(uname);
       if( patientFacade.authenticate( patient ) ){

           loggedIn = true;
           setSessione();
           message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", patient.getUsername());
//           patient = patientFacade.getPatienByUsername(patient);
//            // get Http Session and store username
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("username", getUname());
           FacesContext facesContext = FacesContext.getCurrentInstance();
           
            // Save the uname and password in a cookie
            Cookie btuser = new Cookie("btuser", patient.getUsername());
            Cookie btpasswd = new Cookie("btpasswd",patient.getPassword());
                    if(remember == false){
                        remember1 = "false";
                    }
                    else{
                        remember1 = "true";
                    }
                    Cookie btremember = new Cookie("btremember",remember1);
                    btuser.setMaxAge(3600);
                    btpasswd.setMaxAge(3600);       
            ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(btuser);
            ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(btpasswd);
            ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(btremember);
            
           //return "home.xhtml?faces-redirect=true";

       }else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);              
       // return "index.xhtml?faces-redirect=true";
    }  
     
    public String logout() {
      HttpSession session = SessionUtil.getSession();
      session.invalidate();
      patient = null;
      return "index.xhtml?faces-redirect=true";
   }
    
    public  boolean checkDoctor (){// check if patient has asigned doctor
        
        return true;
    }
    public void checkCookie(){
    FacesContext facesContext = FacesContext.getCurrentInstance();
    String cookieName = null;
    Cookie cookie[] = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
        if(cookie != null && cookie.length > 0){
            for(int i = 0; i<cookie.length; i++){
                cookieName = cookie[i].getName();
                if(cookieName.equals("btuser")){
                    uname = cookie[i].getValue();
                }
                else if(cookieName.equals("btpasswd")){
                   password = cookie[i].getValue();
                }
                else if(cookieName.equals("btremember")){
                    remember1 = cookie[i].getValue();
                    if(remember1.equals("false")){
                        remember = false;
                    }
                    else if(remember1.equals("true")){
                        remember = true;
                    }
                }
            }
        }
        else
            System.out.println("Cannot find any cookie");
    }
    
    
    public PatientBean() {
      patient = new Patient();
      addres = new Addres();
      checkCookie();
    }

    public Addres getAddres() {
        return addres;
    }
    public void setAddres(Addres addres) {
        this.addres = addres;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getRhTypeTemp() {
        return rhTypeTemp;
    }

    public void setRhTypeTemp(String rhTypeTemp) {
        this.rhTypeTemp = rhTypeTemp;
    }

    public String getBlodGrupTemp() {
        return blodGrupTemp;
    }

    public void setBlodGrupTemp(String blodGrupTemp) {
        this.blodGrupTemp = blodGrupTemp;
    }

    public String register() {
            return "thanks?faces-redirect=true";
    }

    
    
    //zobaczyc czy potrzebna
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

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getRemember1() {
        return remember1;
    }

    public void setRemember1(String remember1) {
        this.remember1 = remember1;
    }

    public String getPassword() {
        if (remember == false) {
            password = "";
            return password;
        } else {
            return password;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        if (remember == false) {
            uname = "";
            return uname;
        } else {
            return uname;
        }
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

}
