/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author patryk
 */
//@Named(value = "doctorDay")
//@RequestScoped
public class DoctorDay implements Serializable{
   private static final long serialVersionUID = 1L;
   Medic doctor;
   boolean is = false; // czy lekarz jest dostęny
   boolean patientHasApp = false;//czy pacjent już jest umowiony z tym lekarz na ten dzień
   int dayOfWeak;
   int dayOfYear;//for datatable index
   Date date;
   Date startTime;
   Date endTime;
   int maxApp;
   int bookedApp; //or in the samae day all visits
   String vistType = ""; // typ wizyt domowa czy w przychodni
   int avaibleApp;// ilość wizyt możliwych do rezerwacji

   public DoctorDay(boolean is,int dayOfYear , Date date, String vistType){
      this.date = date;
      this.dayOfYear = dayOfYear;
      this.is = is;
      this.vistType = vistType;
   }
   public DoctorDay(boolean is,int dayOfYear , Date date){
      this.date = date;
      this.dayOfYear = dayOfYear;
      this.is = is;
   }

   public DoctorDay(boolean is,int dayOfYear , Date date, String vistType, Date startTime, Date endTime, int maxApp ){
      this.date = date;
      this.dayOfYear = dayOfYear;
      this.is = is;
      this.vistType = vistType;
      this.startTime = startTime;
      this.endTime = endTime;
      this.maxApp = maxApp;
   }
   public DoctorDay(boolean is ,int dayOfYear,  Date date, String vistType, Date startTime, Date endTime, int maxApp, int bookedApp, Medic doctor ){
      this.date = date;
      this.dayOfYear = dayOfYear;
      this.is = is;
      this.vistType = vistType;
      this.startTime = startTime;
      this.endTime = endTime;
      this.maxApp = maxApp;
      this.doctor = doctor;
   }
   public DoctorDay(){}

   public boolean isIs() {
   return is;
   }

   public void setIs(boolean is) {
   this.is = is;
   }

   public int getDayOfWeak() {
   return dayOfWeak;
   }

   public void setDayOfWeak(int dayOfWeak) {
   this.dayOfWeak = dayOfWeak;
   }

   public Date getDate() {
   return date;
   }

   public void setDate(Date date) {
   this.date = date;
   }

   public Date getStartTime() {
   return startTime;
   }

   public void setStartTime(Date startTime) {
   this.startTime = startTime;
   }

   public Date getEndTime() {
   return endTime;
   }

   public void setEndTime(Date endTime) {
   this.endTime = endTime;
   }

   public int getMaxApp() {
   return maxApp;
   }

   public void setMaxApp(int maxApp) {
   this.maxApp = maxApp;
   }

   public int getBookedApp() {
   return bookedApp;
   }

   public void setBookedApp(int bookedApp) {
   this.bookedApp = bookedApp;
   }

   public String getVistType() {
   return vistType;
   }

   public void setVistType(String vistType) {
   this.vistType = vistType;
   }

   public int getDayOfYear() {
   return dayOfYear;
   }

   public void setDayOfYear(int dayOfYear) {
   this.dayOfYear = dayOfYear;
   }
   public String getCustomeStartTime() {
      if (startTime != null){
      Format formatter = new SimpleDateFormat("HH:mm");
      String s = formatter.format(startTime);
      return s;
      } else return "";
   }
   public String getCustomeEndTime() {
      if (startTime != null){
      Format formatter = new SimpleDateFormat("HH:mm");
      String s = formatter.format(endTime);
      return s;
      } else return "";
   }

   public int getAvaibleApp() {
      return avaibleApp;
   }

   public void setAvaibleApp() {
     this.avaibleApp =  maxApp  - bookedApp;
   }

   public boolean isPatientHasApp() {
      return patientHasApp;
   }

   public void setPatientHasApp(boolean patientHasApp) {
      this.patientHasApp = patientHasApp;
   }

   public Medic getDoctor() {
      return doctor;
   }

   public void setDoctor(Medic doctor) {
      this.doctor = doctor;
   }
    

   
   //Date endTime; 
//   @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//   @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Medic)) {
//            return false;
//        }
//        Medic other = (Medic) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

   @Override
   public String toString() {
      return "pl.kosla.przychodnia.model.DoctorDay [ " + date +" ]";
   }

    
    
}
