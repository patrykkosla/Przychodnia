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

}    