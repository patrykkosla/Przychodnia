package pl.kosla.przychodnia.controler;

import pl.kosla.przychodnia.model.Workhour;
import pl.kosla.przychodnia.controler.util.JsfUtil;
import pl.kosla.przychodnia.controler.util.JsfUtil.PersistAction;
import pl.kosla.przychodnia.session.WorkhourFacade;

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
import pl.kosla.przychodnia.enums.VisitType;

@Named("workhourController")
@SessionScoped
public class WorkhourController implements Serializable {

   @EJB
   private pl.kosla.przychodnia.session.WorkhourFacade ejbFacade;
   private List<Workhour> items = null;
   private Workhour selected;

   public WorkhourController() {
   }

   public Workhour getSelected() {
      return selected;
   }

   public void setSelected(Workhour selected) {
      this.selected = selected;
   }

   protected void setEmbeddableKeys() {
   }

   protected void initializeEmbeddableKey() {
   }

   private WorkhourFacade getFacade() {
      return ejbFacade;
   }
   public VisitType[] getEnum(){
      return VisitType.values();
   }
   
   public Workhour prepareCreate() {
      selected = new Workhour();
      initializeEmbeddableKey();
      return selected;
   }

   public void create() {
      persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("WorkhourCreated"));
      if (!JsfUtil.isValidationFailed()) {
         items = null;    // Invalidate list of items to trigger re-query.
      }
   }

   public void update() {
      persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("WorkhourUpdated"));
   }

   public void destroy() {
      persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("WorkhourDeleted"));
      if (!JsfUtil.isValidationFailed()) {
         selected = null; // Remove selection
         items = null;    // Invalidate list of items to trigger re-query.
      }
   }

   public List<Workhour> getItems() {
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

   public Workhour getWorkhour(java.lang.Integer id) {
      return getFacade().find(id);
   }

   public List<Workhour> getItemsAvailableSelectMany() {
      return getFacade().findAll();
   }

   public List<Workhour> getItemsAvailableSelectOne() {
      return getFacade().findAll();
   }

   @FacesConverter(forClass = Workhour.class)
   public static class WorkhourControllerConverter implements Converter {

      @Override
      public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
         if (value == null || value.length() == 0) {
            return null;
         }
         WorkhourController controller = (WorkhourController) facesContext.getApplication().getELResolver().
                 getValue(facesContext.getELContext(), null, "workhourController");
         return controller.getWorkhour(getKey(value));
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
         if (object instanceof Workhour) {
            Workhour o = (Workhour) object;
            return getStringKey(o.getId());
         } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Workhour.class.getName()});
            return null;
         }
      }

   }

}
