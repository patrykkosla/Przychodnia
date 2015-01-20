/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.kosla.przychodnia.model.LabTestOrder;

/**
 *
 * @author patryk
 */
@Stateless
public class LabTestOrderFacade extends AbstractFacade<LabTestOrder> {
   @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
   private EntityManager em;

   @Override
   protected EntityManager getEntityManager() {
      return em;
   }

   public LabTestOrderFacade() {
      super(LabTestOrder.class);
   }
   public List<LabTestOrder> getSingerAppOrders(int appId){
      Query q = em.createQuery("SELECT l FROM LabTestOrder l WHERE l.appoitmentId.id = :appId", LabTestOrder.class);
      q.setParameter("appId", appId);
      return  q.getResultList();

   }
}
