/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.custom.staff;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import pl.kosla.przychodnia.model.BloodTest;
import pl.kosla.przychodnia.model.LabTestOrder;
import pl.kosla.przychodnia.model.Radiologia;
import pl.kosla.przychodnia.model.RadiologyExamOrder;
import pl.kosla.przychodnia.session.RadiologiaFacade;
import pl.kosla.przychodnia.session.RadiologyExamOrderFacade;
import pl.kosla.przychodnia.utilis.FacesUtils;


@Named(value = "rTGLabExameBean")
@ViewScoped
public class RTGLabExameBean implements Serializable{

    @Inject StaffBean staffBean;
    @EJB RadiologyExamOrderFacade radiologyExamOrderFacade;
    @EJB RadiologiaFacade radiologiaFacade;
    private Radiologia rtgResult;
    private RadiologyExamOrder radiologyExamOrder; 
    private Radiologia tempRTG;
    private Boolean isNew;
    private UploadedFile file;
    
    
   public RTGLabExameBean() {
   }
   @PostConstruct
   private void init() {  
      if( FacesUtils.getFromSession("SelectedRTGOrder") != null){
         setRadiologyExamOrder( (RadiologyExamOrder) FacesUtils.getFromSession("SelectedRTGOrder"));
         
        List<Radiologia> rtgResultList = radiologiaFacade.findRTGTestByOrderId(getRadiologyExamOrder().getId());
          if(rtgResultList.isEmpty() ){
               tempRTG = new Radiologia();
                 if (staffBean.getMedic() != null && staffBean.getMedic().getId() != null){
                    tempRTG.setMedicId(staffBean.getMedic());
                    tempRTG.setExamDate(new Date());
                    tempRTG.setPatientId(getRadiologyExamOrder().getAppoitmentId().getPatientId());
                    tempRTG.setExamOrderId(getRadiologyExamOrder());
                 }
               setIsNew(true);
          }
          else {
             setTempRTG(rtgResultList.get(0));
             setIsNew(false);
             
          }
      }      
   } 
   public void handleFileUpload(FileUploadEvent event) {
      setFile(event.getFile());

        FacesMessage message = new FacesMessage("Succesful ", event.getFile()+ " is uploaded. dlugosc");
        FacesContext.getCurrentInstance().addMessage(null, message);
       

    }
public void saveExame() throws Exception{
   radiologiaFacade.create(tempRTG);
   if(file != null){
      tempRTG.setImagesAmount(1);
      saveFile();
   }      
   else{
      tempRTG.setImagesAmount(0);
   }
}
        
public void saveFile() throws Exception{
   if(file != null){
      String temp = file.getFileName().substring(file.getFileName().length() - 3);
      //file.write("D:\\Roboczy\\medicfilestorage\\"+ tempRTG.getId().toString() + "." + temp );
      file.write("D:\\Roboczy\\medicfilestorage\\"+ tempRTG.getId().toString() + "." + temp );
       
      }
}
   
   
public String create(){
   
   return null;
}
   public Radiologia getRtgResult() {
      return rtgResult;
   }

   public void setRtgResult(Radiologia rtgResult) {
      this.rtgResult = rtgResult;
   }


   public RadiologyExamOrder getRadiologyExamOrder() {
      return radiologyExamOrder;
   }

   public void setRadiologyExamOrder(RadiologyExamOrder radiologyExamOrder) {
      this.radiologyExamOrder = radiologyExamOrder;
   }

   public Radiologia getTempRTG() {
      return tempRTG;
   }

   public void setTempRTG(Radiologia tempRTG) {
      this.tempRTG = tempRTG;
   }

   public Boolean getIsNew() {
      return isNew;
   }

   public void setIsNew(Boolean isNew) {
      this.isNew = isNew;
   }

   public UploadedFile getFile() {
      return file;
   }

   public void setFile(UploadedFile file) {
      this.file = file;
   }
   
}
