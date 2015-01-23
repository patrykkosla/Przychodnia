/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.RadiologyExamOrder;

/**
 *
 * @author patryk
 */
@Stateless
public class RadiologyExamOrderFacade extends AbstractFacade<RadiologyExamOrder> {
   @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
   private EntityManager em;

   @Override
   protected EntityManager getEntityManager() {
      return em;
   }

   public RadiologyExamOrderFacade() {
      super(RadiologyExamOrder.class);
   }
   public List<RadiologyExamOrder> getRadiologyExamOrderAppList(int appoitmentId){
      TypedQuery q = em.createNamedQuery("RadiologyExamOrder.findByAppoitmentId", RadiologyExamOrder.class);
      q.setParameter("appoitmentId", appoitmentId);
      return q.getResultList();
   }
}
