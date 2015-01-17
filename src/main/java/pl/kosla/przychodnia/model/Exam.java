/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author patryk
 */
public abstract class Exam {
   
   
   
   @Column(name = "exam_date")
   @Temporal(TemporalType.TIMESTAMP)
   private Date examDate;
   
}
