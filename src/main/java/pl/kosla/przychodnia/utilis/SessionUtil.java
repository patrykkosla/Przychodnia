
package pl.kosla.przychodnia.utilis;
 
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author patryk
 */
public class SessionUtil {

  public static HttpSession getSession() {
    return (HttpSession)
      FacesContext.
      getCurrentInstance().
      getExternalContext().
      getSession(false);
  }

  public static HttpServletRequest getRequest() {
   return (HttpServletRequest) FacesContext.
      getCurrentInstance().
      getExternalContext().getRequest();
  }

  public static String getUserName()
  {
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    return  session.getAttribute("username").toString();
  }

  public static String getUserId()
  {
    HttpSession session = getSession();
    if ( session != null )
        return (String) session.getAttribute("userid");
    else
        return null;
  }
   public static Cookie[] checkCookie(){
    FacesContext facesContext = FacesContext.getCurrentInstance();
    return  ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
    
   }
}