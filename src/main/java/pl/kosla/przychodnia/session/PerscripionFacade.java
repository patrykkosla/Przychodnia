/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.kosla.przychodnia.model.Perscripion;

/**
 *
 * @author patryk
 */
@Stateless
public class PerscripionFacade extends AbstractFacade<Perscripion> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerscripionFacade() {
        super(Perscripion.class);
    }
    public List<Perscripion> findPerscripionForApp(int appoitmentId){
       Query q = em.createQuery("SELECT p FROM Perscripion p WHERE p.appoitmentId.id = :appoitmentId");
       q.setParameter("appoitmentId", appoitmentId);
       return (List<Perscripion>) q.getResultList();
    }
    public List<Perscripion> findPerscripionForPatient(int patientId, int amount){
       Query q = em.createQuery("SELECT p FROM Perscripion p WHERE p.appoitmentId.patientId.patientId = :patientId");
       q.setParameter("patientId",patientId );
       q.setMaxResults(amount);
       return q.getResultList();
    }
    public List<Perscripion> findPerscripionForPatient(int patientId){
       Query q = em.createQuery("SELECT p FROM Perscripion p WHERE p.appoitmentId.patientId.patientId = :patientId");
       q.setParameter("patientId",patientId );
       return q.getResultList();
    }
}
