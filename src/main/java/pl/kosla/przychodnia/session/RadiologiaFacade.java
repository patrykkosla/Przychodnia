/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.Radiologia;

/**
 *
 * @author patryk
 */
@Stateless
public class RadiologiaFacade extends AbstractFacade<Radiologia> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RadiologiaFacade() {
        super(Radiologia.class);
    }
    public List<Radiologia> getPatintsResults(int patientId, int amount){     
        TypedQuery<Radiologia> q = em.createNamedQuery("Radiologia.findByPatient", Radiologia.class);
        q.setParameter("patientId", patientId);
        //q.setMaxResults(amount);
               
        return q.getResultList();        
    }   
    public List<Radiologia> findRTGTestByOrderId(int orderId){     
        TypedQuery<Radiologia> q = em.createNamedQuery("Radiologia.findByRTGOrderId", Radiologia.class);
        q.setParameter("orderId", orderId);            
        return q.getResultList();        
    }   
    
    
}
