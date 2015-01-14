/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kosla.przychodnia.enums;
import static org.apache.commons.lang3.StringUtils.capitalize;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author patryk
 */
public enum VisitType {
   DOMOWA,
   PRZYCHODNIA,
   WIRTUALNA;
   
   
//   @Override
//   public String toString() {
//        String poprzedniaNazwa = super.toString();
//
//     //   String nowaNazwa = poprzedniaNazwa.toLowerCase();
//        String nowaNazwa = capitalize(poprzedniaNazwa.toLowerCase());
//        return nowaNazwa;
//   }
}
