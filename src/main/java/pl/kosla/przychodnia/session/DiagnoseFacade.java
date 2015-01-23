/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.Diagnose;

/**
 *
 * @author patryk
 */
@Stateless
public class DiagnoseFacade extends AbstractFacade<Diagnose> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnoseFacade() {
        super(Diagnose.class);
    }
    public List<Diagnose> getDiagnosesListForPatient(int patientId, int amount ){
       TypedQuery q = em.createNamedQuery("Diagnose.findByPatientId", Diagnose.class);
       q.setParameter("patientId", patientId);
       q.setMaxResults(amount);
       return q.getResultList();
    }
    public List<Diagnose> getDiagnosesListFoArappoitmentId(int appoitmentId){
       TypedQuery q = em.createNamedQuery("Diagnose.findByAppoitmentId", Diagnose.class);
       q.setParameter("appoitmentId", appoitmentId);
       return q.getResultList();
    }
}
