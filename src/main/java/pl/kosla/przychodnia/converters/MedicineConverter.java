/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import pl.kosla.przychodnia.controler.MedicineController;
import pl.kosla.przychodnia.model.Medicine;

/**
 *
 * @author patryk
 */
@FacesConverter("medicineConverter")
public class MedicineConverter {
   

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try { 
                
                MedicineController service = (MedicineController) fc.getExternalContext().getApplicationMap().get("medicineController");
                return service.getItems().get(Integer.parseInt(value));
                
                
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Medicine) object).getId());
        }
        else {
            return null;
        }
    }   
}    