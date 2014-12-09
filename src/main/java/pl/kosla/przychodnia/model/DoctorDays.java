///*
// * Mój jest kawałek podłogi...  * 
// */
//package pl.kosla.przychodnia.model;
//
//import java.util.Date;
//import java.util.List;
//import org.apache.commons.lang3.time.DateUtils;
//
///**
// *
// * @author patryk
// */
//public class DoctorDays {
//   
//   private int doctorId;
//   private int surgeryId;
//   private List<DoctorDay> dayList;
//   private DoctorDay selectedDoctorDay;
//   
//   
//   
//   
//   class DoctorDay{
//     boolean is = false;
//     int dayOfWeak;
//     Date date;
//     Date startTime;
//     Date endTime;
//     int maxApp;
//     int bookedApp; //or in the samae day all visits
//     String vistType; // typ wizyt domowa czy w przychodni
//
//     public DoctorDay(boolean is , Date date, String vistType){
//         this.date = date;
//         this.is = is;
//     }
//     public DoctorDay(boolean is , Date date){
//         this.date = date;
//         this.is = is;
//     }
//     public DoctorDay(boolean is , Date date, String startTime, String endTime ){
//         this.date = date;
//         this.is = is;
//     }
//     public DoctorDay(boolean is , Date date, String vistType, Date startTime, Date endTime, int maxApp ){
//         this.date = date;
//         this.is = is;
//         this.vistType = vistType;
//         this.startTime = startTime;
//         this.endTime = endTime;
//         this.maxApp = maxApp;
//     }
//     public DoctorDay(boolean is , Date date, String vistType, Date startTime, Date endTime, int maxApp, int bookedApp ){
//         this.date = date;
//         this.is = is;
//         this.vistType = vistType;
//         this.startTime = startTime;
//         this.endTime = endTime;
//         this.maxApp = maxApp;
//     }
//     public DoctorDay(){}
//    }
//   public void addEmpty(Date date){
//        dayList.add(new DoctorDay(
//             false,
//              date
//      ) ); 
//   }
//      public void addOpenDay(Date date, Workhour workhour){
//      String prop = null;
//      Date startTime = null;
//      Date endTime = null;
//      switch (date.getDay()) {
//      case 1:  
//         startTime = workhour.getMondayStart();
//         endTime = workhour.getMondayEnd();
//         prop = workhour.getMondayType();
//         break;
//      case 2:  
//         startTime = workhour.getTuesdayStart();
//         endTime = workhour.getTuesdayEnd();
//         prop = workhour.getTuesdayType();
//         break;
//      case 3:  
//         startTime = workhour.getWednesdayStart();
//         endTime = workhour.getWednesdayEnd();
//         prop = workhour.getWednesdayType();
//         break;
//      case 4:  
//         startTime = workhour.getThursdayStart();
//         endTime = workhour.getThursdayEnd();
//         prop = workhour.getThursdayType();
//         break;
//      case 5:  
//         startTime = workhour.getFridayStart();
//         endTime = workhour.getFridayEnd();
//         prop = workhour.getFridayType();
//         break;
//      }
//      int maxApp = 0;
//      
//      if ( startTime != null && endTime != null){
//         int statH = startTime.getHours();
//         int endH = startTime.getHours();
//         int startM = startTime.getMinutes();
//         int endM = endTime.getMinutes();
//         int temT = (endH -statH) * 60 + (endM - startM);
//         maxApp = temT / 15;// 15 minu na kaÄąÄ˝dego pacjenta
//      }
//      if(prop == null) prop = "";
//      int vistCount = vistCount( date, workhour.getSurgeryHasMedicId().getMedicId().getId(), workhour.getSurgeryHasMedicId().getSurgeryId().getId());
//      //(boolean is , Date date, String vistType, Date startTime, Date endTime, int maxApp, int bookedApp )
//      if(maxApp > 0 ){
//         dayList.add(new DoctorDay(
//             true,
//              date,
//              prop,
//              startTime,
//              endTime,   
//              maxApp,
//              vistCount   
//         ) ); 
//      } else {
//         
//         dayList.add(new DoctorDay(
//             true,
//             date,
//             prop
//                 
//         ) );          
//      }  
//   }
////   public int vistCount(Date date,int doctorId,int surgeryId){
////      String type = "rez";
////      boolean samedate = DateUtils.isSameDay(new Date(), date);  //Takes either Calendar or Date objects
////      if(samedate){
////         return appoitmentFacade.countAppoinments(type, "pas", doctorId, date);
////      }
////      else{
////         return appoitmentFacade.countAppoinments(type, doctorId, date);
////      }    
////   }
//   public int getDoctorId() {
//      return doctorId;
//   }
//
//   public void setDoctorId(int doctorId) {
//      this.doctorId = doctorId;
//   }
//
//   public int getSurgeryId() {
//      return surgeryId;
//   }
//
//   public void setSurgeryId(int surgeryId) {
//      this.surgeryId = surgeryId;
//   }
//
//   public List<DoctorDay> getDayList() {
//      return dayList;
//   }
//
//   public void setDayList(List<DoctorDay> dayList) {
//      this.dayList = dayList;
//   }
//
//   public DoctorDay getSelectedDoctorDay() {
//      return selectedDoctorDay;
//   }
//
//   public void setSelectedDoctorDay(DoctorDay selectedDoctorDay) {
//      this.selectedDoctorDay = selectedDoctorDay;
//   }
//   
//   
//   
//   
//   
//   
//   
//   
//   
//   
//}
