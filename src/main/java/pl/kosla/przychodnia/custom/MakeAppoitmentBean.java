package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import pl.kosla.przychodnia.model.Holidays;
import pl.kosla.przychodnia.model.SurgeryHasMedic;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import pl.kosla.przychodnia.session.HolidaysFacade;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import pl.kosla.przychodnia.model.Workhour;
import pl.kosla.przychodnia.session.WorkhourFacade;
import pl.kosla.przychodnia.utilis.CalendarUtil;

/**
 *
 * @author patryk
 */
@Named(value = "makeAppoitmentBean")
@RequestScoped
public class MakeAppoitmentBean  implements Serializable{

    @EJB private AppoitmentFacade appoitmentFacade;
    @EJB private  SurgeryHasMedic surgeryHasMedic;
    @EJB private  HolidaysFacade holidaysFacade;
    @EJB private  WorkhourFacade workhourFacade;
    
    
    private DateMidnight curentDay;
    private List<DoctorDay> dayList;
    
    public MakeAppoitmentBean() {
      curentDay =  new DateMidnight();
        
    }
    
   public void setAvList(int doctorId,int surgeryId){
      
      int daysToCheck = 7;
      DateMidnight chekedkDate = new DateMidnight(); 
      Workhour workhour= workhourFacade.getWorkhourforMedicinSurgery(doctorId, surgeryId);
      
      for(int n = 0; n <daysToCheck; n++){
        int dw =  chekedkDate.getDayOfWeek();
         boolean weakDay = false;//Pobranie informacji o pracy w zadany dniu tygodnia
         switch (dw) {
         case 1:  
            weakDay = workhour.getMonday();
            break;
         case 2:  
            weakDay =  workhour.getTuesday();
            break;
         case 3:  
            weakDay =  workhour.getWednesday();
            break;
         case 4:  
            weakDay =  workhour.getThursday();
            break;
         case 5:  
            weakDay =  workhour.getFriday();
            break;
         case 6:  
            weakDay =  false;
            break;
         case 7:  
            weakDay =  false;
            break;
         }
         
         if(dw > 5 || !weakDay){//Sprawdza czy doktor pracuje w zadanym dniu tygodnia
            addEmpty(chekedkDate.toDate());
            chekedkDate = chekedkDate.plusDays(1);
            continue;
         }
         Holidays h = holidaysFacade.checkIfDoctorIs(doctorId, chekedkDate.toDate());//sprawdza czy doc nie ma wolnego w ten dzieĂ„Ä…Ă˘â‚¬Ĺľ
         if(h != null){
            //Doktor ma wolne - spawdza na jak dĂ„Ä…Ă˘â‚¬Ĺˇugo 
            int days;     
            DateMidnight endDay = new DateMidnight(h.getEndDate());
            days = Days.daysBetween(chekedkDate, endDay).getDays();
            
            if( (daysToCheck - n) <= days) days = daysToCheck;
            n = n + days;


           for ( int i = 0; i<= days; i++){
              if (dayList.size() < daysToCheck){
                 addEmpty(chekedkDate.toDate());
                 chekedkDate = chekedkDate.plusDays(1);
              }
           }  
           
         }
         else{// doktor pracuje

            // do obliczenia             
            dayList.add(new DoctorDay(
                    true,
                    chekedkDate.toDate()
                    
            )

            );
            chekedkDate = chekedkDate.plusDays(1);
         }  
      }
   }
   public void addEmpty(Date date){
        dayList.add(new DoctorDay(
             false,
              date
      ) ); 
   }
   public void addOpenDay(Date date, Workhour workhour){
      String prop = null;
      Date startTime = null;
      Date endTime = null;
      switch (date.getDay()) {
      case 1:  
         startTime = workhour.getMondayStart();
         endTime = workhour.getMondayEnd();
         prop = workhour.getMondayType();
         break;
      case 2:  
         startTime = workhour.getTuesdayStart();
         endTime = workhour.getTuesdayEnd();
         prop = workhour.getTuesdayType();
         break;
      case 3:  
         startTime = workhour.getWednesdayStart();
         endTime = workhour.getWednesdayEnd();
         prop = workhour.getWednesdayType();
         break;
      case 4:  
         startTime = workhour.getThursdayStart();
         endTime = workhour.getThursdayEnd();
         prop = workhour.getThursdayType();
         break;
      case 5:  
         startTime = workhour.getFridayStart();
         endTime = workhour.getFridayEnd();
         prop = workhour.getFridayType();
         break;
      }
      int maxApp = 0;
      
      if ( startTime != null && endTime != null){
         int statH = startTime.getHours();
         int endH = startTime.getHours();
         int startM = startTime.getMinutes();
         int endM = endTime.getMinutes();
         int temT = (endH -statH) * 60 + (endM - startM);
         maxApp = temT / 15;// 15 minu na kaÄąÄ˝dego pacjenta
      }
      if(prop == null) prop = "";
      int vistCount = vistCount( date, workhour.getSurgeryHasMedicId().getMedicId().getId(), workhour.getSurgeryHasMedicId().getSurgeryId().getId());
      //(boolean is , Date date, String vistType, Date startTime, Date endTime, int maxApp, int bookedApp )
      if(maxApp > 0 ){
         dayList.add(new DoctorDay(
             true,
              date,
              prop,
              startTime,
              endTime,   
              maxApp,
              vistCount   
         ) ); 
      } else {
         
         dayList.add(new DoctorDay(
             true,
             date,
             prop
                 
         ) );          
      }  
   }
   
   public int vistCount(Date date,int doctorId,int surgeryId){
      String type = "rez";
      boolean samedate = DateUtils.isSameDay(new Date(), date);  //Takes either Calendar or Date objects
      if(samedate){
         return appoitmentFacade.countAppoinments(type, "pas", doctorId, date);
      }
      else{
         return appoitmentFacade.countAppoinments(type, doctorId, date);
      }    
   }
   
   
   @PostConstruct
    private void init() {         
    }
    
    public int checkIfDoctorisAcrive(int medicId){
        
        return  0;
    }
    public Boolean[]  checkIfDoctorAvaible(int medicId){
        
        
        return null;
    
    }
    
        
    class DoctorDay{
        boolean is = false;
        int dayOfWeak;
        Date date;
        Date startTime;
        Date endTime;
        int maxApp;
        int bookedApp; //or in the samae day all visits
        String vistType; // typ wizyt domowa czy w przychodni
        
        public DoctorDay(boolean is , Date date, String vistType){
            this.date = date;
            this.is = is;
        }
        public DoctorDay(boolean is , Date date){
            this.date = date;
            this.is = is;
        }
        public DoctorDay(boolean is , Date date, String startTime, String endTime ){
            this.date = date;
            this.is = is;
        }
        public DoctorDay(boolean is , Date date, String vistType, Date startTime, Date endTime, int maxApp ){
            this.date = date;
            this.is = is;
            this.vistType = vistType;
            this.startTime = startTime;
            this.endTime = endTime;
            this.maxApp = maxApp;
        }
        public DoctorDay(boolean is , Date date, String vistType, Date startTime, Date endTime, int maxApp, int bookedApp ){
            this.date = date;
            this.is = is;
            this.vistType = vistType;
            this.startTime = startTime;
            this.endTime = endTime;
            this.maxApp = maxApp;
        }
        public DoctorDay(){}
    }
    public void getDayHourInfo(Date date){
       
       
    }

    
    
    
/**
 *
 * @author patryk
 */
   public AppoitmentFacade getAppoitmentFacade() {
      return appoitmentFacade;
   }

   public void setAppoitmentFacade(AppoitmentFacade appoitmentFacade) {
      this.appoitmentFacade = appoitmentFacade;
   }
    public SurgeryHasMedic getSurgeryHasMedic() {
        return surgeryHasMedic;
    }

    public void setSurgeryHasMedic(SurgeryHasMedic surgeryHasMedic) {
        this.surgeryHasMedic = surgeryHasMedic;
    }

    public HolidaysFacade getHolidaysFacade() {
        return holidaysFacade;
    }

    public void setHolidaysFacade(HolidaysFacade holidaysFacade) {
        this.holidaysFacade = holidaysFacade;
    }

}
