package pl.kosla.przychodnia.utilis;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 *
 * @author patryk
 */
public final class JSFUtils {
	
	public static void addInfoMsg(String componentId, String msg) {
		FacesContext.getCurrentInstance().addMessage(componentId, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	public static void addInfoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	public static void addErrorMsg(String componentId, String msg) {
		FacesContext.getCurrentInstance().addMessage(componentId, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
	
	public static void addErrorMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
}