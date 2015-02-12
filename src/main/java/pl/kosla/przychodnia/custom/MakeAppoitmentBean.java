package pl.kosla.przychodnia.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.time.DateUtils;
import pl.kosla.przychodnia.model.Holidays;
import pl.kosla.przychodnia.session.AppoitmentFacade;
import pl.kosla.przychodnia.session.HolidaysFacade;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import org.primefaces.context.RequestContext;
import pl.kosla.przychodnia.model.Appoitment;
import pl.kosla.przychodnia.model.DoctorDay;
import pl.kosla.przychodnia.model.Workhour;
import pl.kosla.przychodnia.session.WorkhourFacade;

/**
 *
 * @author patryk
 */
@Named(value = "makeAppoitmentBean")
@ViewScoped
public class MakeAppoitmentBean implements Serializable{
   private static final long serialVersionUID = 1L;
   @Inject private PatientBean pb;
  // @Inject private PatientBean doctorDay
   @EJB private AppoitmentFacade appoitmentFacade;
  // @EJB private SurgeryHasMedic surgeryHasMedic;
   @EJB private HolidaysFacade holidaysFacade;
   @EJB private WorkhourFacade workhourFacade;
    
    
   private DateMidnight curentDay;
   private List<DoctorDay> dayList; 
   private DoctorDay selectedDoctorDay;
    
   public MakeAppoitmentBean() {
      curentDay =  new DateMidnight();
      dayList  = new ArrayList <>();
   }
    
   public void setAvList(int doctorId,int surgeryId){
      
      
      int daysToCheck = 7;
      DateMidnight chekedkDate = new DateMidnight(); 
      Workhour workhour= workhourFacade.getWorkhourforMedicinSurgery(doctorId, surgeryId);
      if( workhour == null) return;
      
      for(int n = 0; n <daysToCheck; n++){
        int dw =  chekedkDate.getDayOfWeek();
         boolean weakDay = false;//Pobranie informacji o pracy w zadany dniu tygodnia
         switch (dw) {
         case 1:  weakDay = (  workhour.getMonday() == null ? false : workhour.getMonday() );
            break;
         case 2: weakDay = (  workhour.getTuesday() == null ? false : workhour.getTuesday() );
            break;
         case 3:  weakDay = ( workhour.getWednesday() == null ? false : workhour.getWednesday() );
            break;
         case 4:  weakDay =  ( workhour.getThursday() == null ? false : workhour.getThursday() );
            break;
         case 5:  weakDay =  ( workhour.getFriday() == null ? false : workhour.getFriday() );
            break;
         case 6: weakDay =  false;
            break;
         case 7: weakDay =  false;
            break;
         default: weakDay =  false;
            break;
         }
         
         if(dw > 5 || !weakDay){//Sprawdza czy doktor pracuje w zadanym dniu tygodnia
            addEmpty(chekedkDate.toDate());
            chekedkDate = chekedkDate.plusDays(1);
            continue;
         }
         if ( holidaysFacade.checkIfDoctorIs(doctorId, chekedkDate.toDate()) != null) {//sprawdza czy doc nie ma wolnego w ten dzieĂ„Ä…Ă˘â‚¬Ĺľ
            Holidays h = holidaysFacade.checkIfDoctorIs(doctorId, chekedkDate.toDate());
            //Doktor ma wolne - spawdza na jak dlugo
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
         }else{// add working day set start and and h
            addOpenDay(chekedkDate.toDate(), workhour );
            chekedkDate = chekedkDate.plusDays(1);
         }  
      }
      for (DoctorDay d : dayList){
         //dodaje info o wolnych miejscach
         d.setAvaibleApp();
         
         //dodać sprawdzenie czy pacjent nie jest już zarejesrowany w ten dzień
         d.setPatientHasApp(appoitmentFacade.checkiIfPatientGatApp(Appoitment.getREZERWACJA(), pb.getPatient().getPatientId(), doctorId, d.getDate()));
      }
      
   }
   public void addEmpty(Date date){
      //System.out.println ("pusty"); 
        dayList.add(new DoctorDay(
            false,
            new DateMidnight(date).getDayOfYear(),  
            date
      ) ); 
   }
   public void addOpenDay(Date date, Workhour workhour){
      String prop = null;
      Date startTime = null;
      Date endTime = null;
      int maxApp = 0;
      switch (date.getDay()) {
      case 1:  
         startTime = workhour.getMondayStart();
         endTime = workhour.getMondayEnd();
         prop = workhour.getMondayType();
         maxApp = (workhour.getMondayMaxApp() != null) ?  workhour.getMondayMaxApp() : 0;
         break;
      case 2:  
         startTime = workhour.getTuesdayStart();
         endTime = workhour.getTuesdayEnd();
         prop = workhour.getTuesdayType();
         maxApp = (workhour.getTuesdayMaxApp() != null)  ?  workhour.getTuesdayMaxApp() : 0;
         break;
      case 3:  
         startTime = workhour.getWednesdayStart();
         endTime = workhour.getWednesdayEnd();
         prop = workhour.getWednesdayType();
         maxApp = (workhour.getWednesdayMaxApp() != null)  ?  workhour.getWednesdayMaxApp() : 0;
         break;
      case 4:  
         startTime = workhour.getThursdayStart();
         endTime = workhour.getThursdayEnd();
         prop = workhour.getThursdayType();
         maxApp = (workhour.getThursdayMaxApp() != null)  ?  workhour.getThursdayMaxApp() : 0;
         break;
      case 5:  
         startTime = workhour.getFridayStart();
         endTime = workhour.getFridayEnd();
         prop = workhour.getFridayType();
         maxApp = (workhour.getFridayMaxApp() != null) ?  workhour.getFridayMaxApp() : 0;
         break;
      }
//      if ( startTime != null && endTime != null){
//         int statH = startTime.getHours();
//         int endH = startTime.getHours();
//         int startM = startTime.getMinutes();
//         int endM = endTime.getMinutes();
//         int temT = (endH -statH) * 60 + (endM - startM);
//         maxApp = temT / 15;// 15 minu na kaÄąÄ˝dego pacjenta
//      }
      
      if(prop == null) prop = "";
      int vistCount = vistCount( date, workhour.getSurgeryHasMedicId().getMedicId().getId());
      dayList.add(new DoctorDay(
          true,
           new DateMidnight(date).getDayOfYear(),     
           date,
           prop,
           startTime,
           endTime,   
           maxApp,
           vistCount,
           workhour.getSurgeryHasMedicId().getMedicId()// doctor
      ) ); 
   }
   
   public int vistCount(Date date,int doctorId){
      boolean samedate = DateUtils.isSameDay(new Date(), date);  //Takes either Calendar or Date objects
      if(samedate){
         return appoitmentFacade.countAppoinments(Appoitment.REZERWACJA, Appoitment.PAST, doctorId, date);
      }
      else{
         return appoitmentFacade.countAppoinments(Appoitment.REZERWACJA, doctorId, date);
      }    
   }
     public String  bookAppoitmentTest(DoctorDay item ){
      if(item == null){
          System.out.println("Brak lekarzaq");
      }
     return "dup";
     } 
   public String bookAppoitment(DoctorDay d){
      selectedDoctorDay = d;
      RequestContext context = RequestContext.getCurrentInstance();
      if(selectedDoctorDay == null){
          System.out.println("Brak lekarzaq");
      }
      
      Appoitment ap = new Appoitment();
      ap.setMedicId(selectedDoctorDay.getDoctor());
      ap.setPatientId(pb.getPatient());
      ap.setStatus(Appoitment.REZERWACJA);
      ap.setAppoitmentDate(selectedDoctorDay.getDate());
      Integer temp = appoitmentFacade.bookAppoitment(ap, selectedDoctorDay.getMaxApp());
      
 
      FacesMessage message = null;
      boolean bookSet = false;
      if(temp == 0){
         message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Przykro nam", "Nie ma już wolnych miejsc");
         bookSet = false;
      }else if(temp < 0) {
         message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Uwaga", "Nie udało się dokań rezerwacji");
         bookSet = false;
      }else{
         message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Potwierdzenie wizyty", "Twój nomer rejestracji to: " + temp.toString() );
         bookSet = true;
      }
      FacesContext.getCurrentInstance().addMessage(null, message);
      context.addCallbackParam("bookSet", bookSet);  
      context.addCallbackParam("bookNumber", temp);  
      return null;
   }
   public String cancelBookAppoitment(DoctorDay d){
      selectedDoctorDay = d;
      Appoitment a = appoitmentFacade.finOneAppoitment(pb.getPatient().getPatientId(), d.getDate(), d.getDoctor().getId());
      a.setStatus(Appoitment.CANCELD);
      appoitmentFacade.edit(a);
      RequestContext context = RequestContext.getCurrentInstance();
      context.addCallbackParam("bookSet", true);  
      return null;
   }
   @PostConstruct
   public void init() {  
       if(pb != null && pb.getPatient() != null){
          if(pb.getPatient().getMedicId() != null && pb.getPatient().getSurgeryId() != null){
            setAvList(pb.getPatient().getMedicId().getId() , pb.getPatient().getSurgeryId().getId());
             
          }else{
             
          }
       } 
    }
   
   public String refershView(){
      return null;
   }
   
   public HolidaysFacade getHolidaysFacade() {
        return holidaysFacade;
    }

   public void setHolidaysFacade(HolidaysFacade holidaysFacade) {
        this.holidaysFacade = holidaysFacade;
    }

   public List<DoctorDay> getDayList() {
//      for(DoctorDay n : dayList){
//          System.out.println (n.getDayOfYear()); 
//      }
      return dayList;
   }

   public void setDayList(List<DoctorDay> dayList) {
      this.dayList = dayList;
   }

   public DoctorDay getSelectedDoctorDay() {
      return selectedDoctorDay;
   }

   public void setSelectedDoctorDay(DoctorDay selectedDoctorDay) {
      this.selectedDoctorDay = selectedDoctorDay;
   }
   
}
