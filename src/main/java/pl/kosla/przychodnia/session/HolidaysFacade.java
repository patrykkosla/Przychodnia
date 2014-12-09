/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import pl.kosla.przychodnia.model.Holidays;
/**
 *
 * @author patryk
 */
@Stateless
public class HolidaysFacade extends AbstractFacade<Holidays> {
    @PersistenceContext(unitName = "pl.kosla_przychodnia_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HolidaysFacade() {
        super(Holidays.class);
    }
    public Holidays checkIfDoctorIs(int medicId, Date date){       
        TypedQuery<Holidays> query = getEntityManager().createNamedQuery("Holidays.checkIfDoctorIs",Holidays.class);
        query.setParameter("medicId", medicId);
        query.setParameter("date", date, TemporalType.DATE);
        query.setMaxResults(1);

        //return query.getResultList().get(0);
        List<Holidays> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
