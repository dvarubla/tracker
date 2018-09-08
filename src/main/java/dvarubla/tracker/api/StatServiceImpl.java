package dvarubla.tracker.api;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Service
public class StatServiceImpl implements StatService {
    @PersistenceContext
    private EntityManager _entityManager;

    @Override
    public BigDecimal getTotalMoney() {
        return (BigDecimal)
                _entityManager.unwrap(Session.class)
                        .createQuery("select coalesce(sum(totalCost), 0) from Purchase")
                        .getSingleResult();
    }
}
