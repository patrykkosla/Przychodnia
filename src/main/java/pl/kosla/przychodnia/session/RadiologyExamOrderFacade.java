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
   public RadiologyExamOrder findRTGOrdeById(int orderId){
      TypedQuery q = em.createNamedQuery("RadiologyExamOrder.findById", RadiologyExamOrder.class);
      q.setParameter("id", orderId);
      List<RadiologyExamOrder> t =  q.getResultList();
      if(t.isEmpty()){
      return null;
       }
      else {
        return (RadiologyExamOrder)q.getResultList().get(0); 
      }
      
   }
}
