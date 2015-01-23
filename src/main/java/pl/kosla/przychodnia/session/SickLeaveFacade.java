/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.SickLeave;

/**
 *
 * @author patryk
 */
@Stateless
public class SickLeaveFacade extends AbstractFacade<SickLeave> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SickLeaveFacade() {
        super(SickLeave.class);
    }
    public List<SickLeave> getSickLeavesLitByAppoitmentId(int appoitmentId){
       TypedQuery q = em.createNamedQuery("SickLeave.findByAppoitmentId", SickLeave.class);
       q.setParameter("appoitmentId",appoitmentId);
       return q.getResultList();     
    }
    public List<SickLeave> getSickLeavesLitByPatientId(int patientId){
       TypedQuery q = em.createNamedQuery("SickLeave.findByPatientId", SickLeave.class);
       q.setParameter("patientId",patientId);
       return q.getResultList();     
    }
}
