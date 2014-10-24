/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
