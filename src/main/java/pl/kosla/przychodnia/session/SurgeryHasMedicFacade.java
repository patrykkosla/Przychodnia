/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.enums.Positione;
import pl.kosla.przychodnia.model.Medic;
import pl.kosla.przychodnia.model.Surgery;
import pl.kosla.przychodnia.model.SurgeryHasMedic;

/**
 *
 * @author patryk
 */
@Stateless
public class SurgeryHasMedicFacade extends AbstractFacade<SurgeryHasMedic> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SurgeryHasMedicFacade() {
        super(SurgeryHasMedic.class);
    }

    public List<Surgery> findAllSurgery(Medic medic) {
        TypedQuery<Surgery> q = em.createNamedQuery("SurgeryHasMedic.findSugeryForMedic", Surgery.class);
        q.setParameter("medicId", medic );
        return q.getResultList();
    }

   /**
    * Find all Surgery for  specific medic
    * @param medic - id medica 
    * @param isAtive - status aktywności bool
    * @return
    */
   public List<Surgery> findAllSurgeryFormMedicStatus(int medicId, boolean isAtive) {
        TypedQuery<Surgery> q = em.createNamedQuery("SurgeryHasMedic.findActiveSurgeryforMedic", Surgery.class);
        q.setParameter("medicId", medicId );
        q.setParameter("isAtive", isAtive );
        return q.getResultList();
        
       // @NamedQuery(name = "SurgeryHasMedic.findActiveSurgeryforMedic",
  // query = "SELECT shm.surgeryId FROM SurgeryHasMedic shm WHERE shm.medicId.id = :medic AND shm.isAtive = :isAtive" ),
   
    }
//    public List<Medic> findAllMedicForSurgery(Surgery surgery) {
//       // TypedQuery<Surgery> q = em.createNamedQuery("SurgeryHasMedic.findSugeryForMedic", Medic.class);
//        //q.setParameter("medicId", medic );
//        //return q.getResultList();
//    }
public List<SurgeryHasMedic> finaByPositineIsActive(Positione positione , boolean isAtive){
      TypedQuery<SurgeryHasMedic> q = em.createNamedQuery( "SurgeryHasMedic.finaByPositineIsActive", SurgeryHasMedic.class);
      q.setParameter("positione", positione);
      q.setParameter("isAtive", isAtive);
     return q.getResultList();
   
}
public List<SurgeryHasMedic> finaByMedicIsActive(Integer medicId , boolean isAtive){
      TypedQuery<SurgeryHasMedic> q = em.createNamedQuery( "SurgeryHasMedic.findByMedicId", SurgeryHasMedic.class);
      q.setParameter("medicId", medicId);
      q.setParameter("isAtive", isAtive);
     return q.getResultList();
}
public List<SurgeryHasMedic> finaByMedicIsActiveEmpty(Integer medicId , boolean isAtive){
      TypedQuery<SurgeryHasMedic> q = em.createNamedQuery( "SurgeryHasMedic.findByMedicIdEmpty", SurgeryHasMedic.class);
      q.setParameter("medicId", medicId);
      q.setParameter("isAtive", isAtive);
     return q.getResultList();
}

}    