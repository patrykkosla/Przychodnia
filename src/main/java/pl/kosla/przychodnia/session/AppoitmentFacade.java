/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.Appoitment;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author patryk
 */
@Stateless
public class AppoitmentFacade extends AbstractFacade<Appoitment> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppoitmentFacade() {
        super(Appoitment.class);
    }
    public List<Appoitment> getBookedAppointment(int days, int medicId){    
        // Dodawanie czasu prze GregorianCalendar
        //Calendar calendar = new GregorianCalendar();
        //calendar.add(Calendar.DATE, days);
        //Date endDate = calendar.getTime();
       Date currentDate = new Date();
        //Dodanie czasu z wykorzystaniem biblioteki org.apache.commons.
        Date endDate = DateUtils.addDays(currentDate, days);     
        TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findFutherBooked", Appoitment.class);
        q.setParameter("status", "rez");
        q.setParameter("medicId", medicId);
        q.setParameter("startDate", currentDate, TemporalType.DATE);
        q.setParameter("endDate", endDate, TemporalType.DATE);
        return q.getResultList();
    }
    
    public List<Appoitment> getAppointmentForMedic(String stat, int medicId){
        TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findMedicStatus", Appoitment.class);
        q.setParameter("status", stat);
        q.setParameter("medicId", medicId);
        return q.getResultList();      
    }
    
    public List<Appoitment> getAppointmentForMedic(String stat, int medicId, int patientId){
        TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findMedicPatientStatus", Appoitment.class);
        q.setParameter("status", stat);
        q.setParameter("medicId", medicId);
        q.setParameter("patientId", patientId);    
        return q.getResultList();      
    }
    
    public List<Appoitment> getAppointmentForPatient(String stat, int patientId){
        TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findPatientAppoitment", Appoitment.class);
        q.setParameter("status", stat);
        q.setParameter("patientId", patientId);
        return q.getResultList();      
    }
    
    public List<Appoitment> getLastAppointmentsForPatient(String stat, int patientId, int take){
        TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findLastPatientAppoitment", Appoitment.class);
        q.setMaxResults(take);
        q.setParameter("status", stat);
        q.setParameter("patientId", patientId);
        return q.getResultList();      
    }

    public int countAppoinments(String stat,  int medicId, Date appDate){
      //http://stackoverflow.com/questions/11896872/jpa-count-namedquery
      //int count = ((Number)em.createNamedQuery("Charakteristika.findAllCount").getSingleResult()).intValue();
        int count = ((Number)em.createNamedQuery("Appoitment.countApp")
                .setParameter("status", stat)
                .setParameter("medicId", medicId)
                .setParameter("appDate", appDate)
                .getSingleResult()).intValue();
       return count;
    }
    
    public int countAppoinments(String status, String statusBis,  int medicId, Date appDate){
       int count = ((Number)em.createNamedQuery("Appoitment.countAppType")
          .setParameter("status", status)
          .setParameter("statusBis", statusBis)
          .setParameter("medicId", medicId)
          .setParameter("appDate", appDate, TemporalType.DATE)
          .getSingleResult()).intValue();
       return count;
    }
public int countPatientApp(int paiientId, String stat){
         int count = ((Number)em.createNamedQuery("Appoitment.countPatientApp")
          .setParameter("patientId", paiientId)
          .setParameter("status", stat)
          .getSingleResult()).intValue();
       return count;    
   } 
public boolean checkiIfPatientGatApp(String status, int patientId, int medicId, Date appDate){
      int count = ((Number)em.createNamedQuery("Appoitment.checkiIfPatientGatApp")
       .setParameter("status", status)
       .setParameter("medicId", medicId)
       .setParameter("patientId", patientId)
       .setParameter("appDate", appDate, TemporalType.DATE)
       .getSingleResult()).intValue();
      return count > 0 ? true : false;
  }

public Integer bookAppoitment(Appoitment appoitment, int maxApp){
   // zaraca numer w kolejce jeśłi zarezerwował lub 0 jeśli nie udało się zabookować wizyty
   

     Query  q = em.createQuery("SELECT a.queuePositione FROM Appoitment a WHERE a.status = :status AND a.medicId.id = :medicId AND a.appoitmentDate =:appDate ORDER BY a.queuePositione");
     q.setParameter("status", Appoitment.REZERWACJA);
     q.setParameter("medicId", appoitment.getMedicId().getId());
     q.setParameter("appDate", appoitment.getAppoitmentDate(), TemporalType.DATE); 
     
     Integer queuePositione = 0;
     List<Integer> results = (List<Integer>) q.getResultList();
     if(results.isEmpty()){
        queuePositione = 1;
     }else if(results.size() >= maxApp){
        queuePositione = 0;
     }else{
      Integer prev = results.get(0);
      Integer curent = 0;

      for (Integer result : results) {
         curent = result;
          if(  curent  > prev++) {
             queuePositione = prev + 1;
          }  
          prev = curent;
      }
      if(queuePositione == 0)  {
         queuePositione = curent ++;
      } 
     }  
     if(queuePositione > 0){
      appoitment.setQueuePositione(queuePositione);
      em.persist(appoitment);
     }
           System.out.println(queuePositione.toString());
    return queuePositione;  
}

   /**
    *
    * @param patient
    * @param date
    * @param doctor
    * @return Appoitment
    */
   public Appoitment finOneAppoitment(int patient, Date date, int doctor){
     TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findAppoitmentByPatientMedicStausDate", Appoitment.class);
     q.setParameter("status", Appoitment.REZERWACJA);
     q.setParameter("patientId", patient);
     q.setParameter("medicId", doctor);
     q.setParameter("appDate", date, TemporalType.DATE); 
     Appoitment a =q.getResultList().get(0);       
     return a;
   }
   public void  cancelAppoitment(Appoitment a){
   }

   /**
    * Find appotimens for singel day tha are booked to selected doctor
    *
    * @param doctorId
    * @param surgeryId
    * @param appDate
    * @param status
    * @return
    */
   public List<Appoitment> allDocAppForSingelDay(Integer doctorId, Integer surgeryId, Date appDate, String status){
      TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.ttt", Appoitment.class);
      q.setParameter("medicId", doctorId);
      q.setParameter("surgeryId", surgeryId);
      q.setParameter("status", status);
      q.setParameter("appDate", appDate, TemporalType.DATE);       
      return q.getResultList();

   }
   public List<Appoitment> allDocAppForSingelDay(Integer doctorId, Integer surgeryId, Date appDate, String status, String statusBis){
      TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findDocDayAllapp", Appoitment.class);
      q.setParameter("medicId", doctorId);
      q.setParameter("surgeryId", surgeryId);
      q.setParameter("status", status);
      q.setParameter("statusBis", statusBis);
      q.setParameter("appDate", appDate, TemporalType.DATE);       
      return q.getResultList();

   }
   public Appoitment findById(Integer appId){
      TypedQuery<Appoitment> q = em.createNamedQuery("Appoitment.findById", Appoitment.class);
      q.setParameter("id", appId);
      return q.getSingleResult();
   }
   public List<Appoitment> getListofOldBookedAppp(){
     //      Query  q = em.createQuery("SELECT a.queuePositione FROM Appoitment a WHERE a.status = :status AND a.medicId.id = :medicId AND a.appoitmentDate =:appDate ORDER BY a.queuePositione");
     Date d = new Date();
     Query q = em.createQuery("SELECT a FROM Appoitment a WHERE a.status = :status AND a.appoitmentDate < :appDate");
     q.setParameter("status", Appoitment.REZERWACJA);
     q.setParameter("appDate", d, TemporalType.DATE); 
     return q.getResultList();
   }
} 