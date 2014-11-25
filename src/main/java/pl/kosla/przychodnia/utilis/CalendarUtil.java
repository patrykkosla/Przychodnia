/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.utilis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patryk
 */
public class CalendarUtil {
   
   
  static public String intDayToStringDayOfTheWeak(int day){
       if(day > 0 && day < 6){
          String weakDay = "";
          
            switch (day) {
         case 1:  
            weakDay =  "monday";
            break;
         case 2:  
            weakDay =  "tuesday";
            break;
         case 3:  
            weakDay =  "wednesday";
            break;
         case 4:  
            weakDay =  "thursday";
            break;
         case 5:  
            weakDay =  "friday";
            break;

         default: return weakDay;   
         }
         return weakDay;   
      }
       else return null;
   } 

  static public List<String> getWorkHourPropertys( String workhour){
     List<String> temp = new ArrayList<>();
     temp.add(workhour.substring(0, 3));
     temp.add(workhour.substring(3, 6));
     temp.add(workhour.substring(6));
     
     return temp;
  }
}

