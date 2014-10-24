/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
