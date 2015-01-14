package pl.kosla.przychodnia.controler;

import pl.kosla.przychodnia.model.SurgeryHasMedic;
import pl.kosla.przychodnia.controler.util.JsfUtil;
import pl.kosla.przychodnia.controler.util.JsfUtil.PersistAction;
import pl.kosla.przychodnia.session.SurgeryHasMedicFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pl.kosla.przychodnia.enums.Positione;
import pl.kosla.przychodnia.model.Medic;
import pl.kosla.przychodnia.model.Surgery;
import static pl.kosla.przychodnia.model.SurgeryHasMedic_.positione;

@Named("surgeryHasMedicController")
@SessionScoped
public class SurgeryHasMedicController implements Serializable {

    @EJB
    private pl.kosla.przychodnia.session.SurgeryHasMedicFacade ejbFacade;
    private List<SurgeryHasMedic> items = null;
    private SurgeryHasMedic selected;

    public SurgeryHasMedicController() {
    }

    public SurgeryHasMedic getSelected() {
        return selected;
    }

    public void setSelected(SurgeryHasMedic selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SurgeryHasMedicFacade getFacade() {
        return ejbFacade;
    }

    public SurgeryHasMedic prepareCreate() {
        selected = new SurgeryHasMedic();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SurgeryHasMedicCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SurgeryHasMedicUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SurgeryHasMedicDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SurgeryHasMedic> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public SurgeryHasMedic getSurgeryHasMedic(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SurgeryHasMedic> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SurgeryHasMedic> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    public List<SurgeryHasMedic> getDoctorsAvailableSelectOne() {
        return getFacade().finaByPositineIsActive(Positione.DOCTOR , true);
    }

    @FacesConverter(forClass = SurgeryHasMedic.class)
    public static class SurgeryHasMedicControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SurgeryHasMedicController controller = (SurgeryHasMedicController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "surgeryHasMedicController");
            return controller.getSurgeryHasMedic(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SurgeryHasMedic) {
                SurgeryHasMedic o = (SurgeryHasMedic) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SurgeryHasMedic.class.getName()});
                return null;
            }
        }

    }
  public List<Surgery> findAllSurgeryForMedic(Medic medic) {
      return getFacade().findAllSurgery(medic);
  }
  public List<Medic> findAllMedicForSurgery(Surgery surgery){
       return null;
  }
         
}
