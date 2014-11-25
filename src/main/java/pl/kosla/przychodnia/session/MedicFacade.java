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
    public boolean authenticate(Medic medic) {

       TypedQuery<Medic> query = getEntityManager().createNamedQuery("Medic.login",Medic.class);
        query.setParameter("username", medic.getUsername());
        query.setParameter("password", medic.getPassword());

        List<Medic> listaPacjentow = query.getResultList();
        if(listaPacjentow.isEmpty()){
            return false;
        }
        return true;       
    }
    public Medic getMedicByUsername(Medic medic){
        TypedQuery<Medic> q = em.createNamedQuery("Medic.findByUsername", Medic.class);
        q.setParameter("username", medic.getUsername() );
        return q.getSingleResult();
    }    
    
}
