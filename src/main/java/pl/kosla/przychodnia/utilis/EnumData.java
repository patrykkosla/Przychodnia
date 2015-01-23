/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.utilis;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import pl.kosla.przychodnia.enums.RadiologyExamPart;
import pl.kosla.przychodnia.enums.RadiologyExamType;
import pl.kosla.przychodnia.enums.VisitType;

/**
 *
 * @author patryk
 */
@Named(value = "enumData")
@ApplicationScoped
public class EnumData  implements Serializable{

   /**
    * Creates a new instance of EnumData
    */
   public EnumData() {
   }
   public static VisitType[] getEnumVisitType(){
      return  VisitType.values();
   }
   public static RadiologyExamPart[] getEnumRadiologyExamPart(){
      return RadiologyExamPart.values();
   }
   public static RadiologyExamType[] getEnumRadiologyExamType(){
      return RadiologyExamType.values();
   }
}
