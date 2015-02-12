/*
 * StaffBean jest dla pielęgniarek ma obsługiwać widok na przychodnie etc
 */
package pl.kosla.przychodnia.custom.staff;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import pl.kosla.przychodnia.enums.MedicType;
import pl.kosla.przychodnia.model.Medic;
import pl.kosla.przychodnia.session.MedicFacade;
import pl.kosla.przychodnia.utilis.SessionUtil;
/**
 *
 * @author patryk
 */
@Named(value = "staffBean")
@SessionScoped
public class StaffBean implements Serializable {

    @EJB private MedicFacade medicFacade;
    /**
     * Creates a new instance of StaffBean
     */
    public StaffBean() {  
      medic = new Medic();
      checkCookie();
    }
    private Medic medic;
    private String password;
    private String uname;
    
    //zapamiętywanie chasła
    boolean remember;
    String remember1 = "";
    
    public void login(ActionEvent event) {//ActionEvent event
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false; 
      
        medic.setPassword(password);
        medic.setUsername(uname);
         System.out.println("password: " +password);
         System.out.println("uname: " +uname);
        String role;  
       if( medicFacade.authenticate( medic ) ){

           loggedIn = true;
           medic = medicFacade.getMedicByUsername(medic);
           message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", medic.getUsername());
            
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("username", medic.getUsername());
            session.setAttribute("role", medic.getType());
                
            FacesContext facesContext = FacesContext.getCurrentInstance();
           
            // Save the uname and password in a cookie
            Cookie btuser = new Cookie("btuser", medic.getUsername());
            Cookie btpasswd = new Cookie("btpasswd",getPassword());
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

            role = medic.getType().toString();
            
       }else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            role = "error";
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);              
        context.addCallbackParam("role", role);              
    } 
   public String logout() {
      HttpSession session = SessionUtil.getSession();
      session.invalidate();
      medic = null;
      return "/index.xhtml?faces-redirect=true";
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
    public MedicFacade getMedicFacade() {
        return medicFacade;
    }

    public void setMedicFacade(MedicFacade medicFacade) {
        this.medicFacade = medicFacade;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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
}
