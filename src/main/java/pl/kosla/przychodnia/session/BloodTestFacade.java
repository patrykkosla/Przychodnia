/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.BloodTest;

/**
 *
 * @author patryk
 */
@Stateless
public class BloodTestFacade extends AbstractFacade<BloodTest> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BloodTestFacade() {
        super(BloodTest.class);
    }
    public List<BloodTest> getPatintsResults(int patinetId, int amount){     
        TypedQuery<BloodTest> q = em.createNamedQuery("BloodTest.findByPatient", BloodTest.class);
        q.setParameter("patientId", patinetId);
        q.setMaxResults(amount);
               
        return q.getResultList();        
    }
    
}
