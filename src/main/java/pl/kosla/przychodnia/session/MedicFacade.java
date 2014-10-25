/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.Medic;

/**
 *
 * @author patryk
 */
@Stateless
public class MedicFacade extends AbstractFacade<Medic> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicFacade() {
        super(Medic.class);
    }
    
    public List<Medic> mediForSurgery(int id){
        TypedQuery<Medic> query = getEntityManager().createNamedQuery("Medic.findAllActiveMedicForSelectedSurgery",Medic.class);
         query.setParameter("surgeryId", id);
         query.setParameter("isAtive", true);
         query.setParameter("enable", true);
         query.setParameter("type", "doc");
        List<Medic> ListaLekarzy = query.getResultList();
        
        return ListaLekarzy;
    }
    
}
