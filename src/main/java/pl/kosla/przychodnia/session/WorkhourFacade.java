/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.Workhour;

/**
 *
 * @author patryk
 */
@Stateless
public class WorkhourFacade extends AbstractFacade<Workhour> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkhourFacade() {
        super(Workhour.class);
    }
   public Workhour getWorkhourforMedicinSurgery(int medicId, int surgeryId){
        TypedQuery<Workhour> q = em.createNamedQuery("Workhour.findByMedicId&SurgeryId", Workhour.class);
        q.setMaxResults(1);
        q.setParameter("medicId", medicId);
        q.setParameter("surgeryId", surgeryId);
        q.setParameter("isActive", true);
        return q.getResultList().get(1);      
    }
}
