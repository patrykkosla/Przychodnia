package pl.kosla.przychodnia.controler;

import pl.kosla.przychodnia.model.LabTestOrder;
import pl.kosla.przychodnia.controler.util.JsfUtil;
import pl.kosla.przychodnia.controler.util.JsfUtil.PersistAction;
import pl.kosla.przychodnia.session.LabTestOrderFacade;

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

@Named("labTestOrderController")
@SessionScoped
public class LabTestOrderController implements Serializable {

   @EJB
   private pl.kosla.przychodnia.session.LabTestOrderFacade ejbFacade;
   private List<LabTestOrder> items = null;
   private LabTestOrder selected;

   public LabTestOrderController() {
   }

   public List<LabTestOrder> getOrdersAppList(int appId){
     return getFacade().getSingerAppOrders(appId);
   }
           
   public LabTestOrder getSelected() {
      return selected;
   }

   public void setSelected(LabTestOrder selected) {
      this.selected = selected;
   }

   protected void setEmbeddableKeys() {
   }

   protected void initializeEmbeddableKey() {
   }

   private LabTestOrderFacade getFacade() {
      return ejbFacade;
   }

   public LabTestOrder prepareCreate() {
      selected = new LabTestOrder();
      initializeEmbeddableKey();
      return selected;
   }

   public void create() {
      persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LabTestOrderCreated"));
      if (!JsfUtil.isValidationFailed()) {
         items = null;    // Invalidate list of items to trigger re-query.
      }
   }

   public void update() {
      persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LabTestOrderUpdated"));
   }

   public void destroy() {
      persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LabTestOrderDeleted"));
      if (!JsfUtil.isValidationFailed()) {
         selected = null; // Remove selection
         items = null;    // Invalidate list of items to trigger re-query.
      }
   }

   public List<LabTestOrder> getItems() {
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

   public LabTestOrder getLabTestOrder(java.lang.Integer id) {
      return getFacade().find(id);
   }

   public List<LabTestOrder> getItemsAvailableSelectMany() {
      return getFacade().findAll();
   }

   public List<LabTestOrder> getItemsAvailableSelectOne() {
      return getFacade().findAll();
   }

   @FacesConverter(forClass = LabTestOrder.class)
   public static class LabTestOrderControllerConverter implements Converter {

      @Override
      public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
         if (value == null || value.length() == 0) {
            return null;
         }
         LabTestOrderController controller = (LabTestOrderController) facesContext.getApplication().getELResolver().
                 getValue(facesContext.getELContext(), null, "labTestOrderController");
         return controller.getLabTestOrder(getKey(value));
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
         if (object instanceof LabTestOrder) {
            LabTestOrder o = (LabTestOrder) object;
            return getStringKey(o.getId());
         } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LabTestOrder.class.getName()});
            return null;
         }
      }

   }

}
