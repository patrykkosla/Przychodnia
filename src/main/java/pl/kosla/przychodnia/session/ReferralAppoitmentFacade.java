/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.kosla.przychodnia.model.ReferralAppoitment;

/**
 *
 * @author patryk
 */
@Stateless
public class ReferralAppoitmentFacade extends AbstractFacade<ReferralAppoitment> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReferralAppoitmentFacade() {
        super(ReferralAppoitment.class);
    }
    
}
