package dvarubla.tracker.api;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public UnitPlotResponse getOnePlot(String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateSerializer.DATE_FORMAT_PATTERN);
        LocalDateTime startDate = LocalDate.parse(startDateStr, formatter).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(endDateStr, formatter).atStartOfDay().plusDays(1).minusSeconds(1);
        List list = _entityManager.unwrap(Session.class).createQuery(
                "select sum(totalCost), trunc(purchaseDate, 'DDD') from Purchase " +
                        "where purchaseDate >= :startDate and purchaseDate <= :endDate "+
                        "group by trunc(purchaseDate, 'DDD') order by 2"
        ).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
        List<LocalDate> labels = new ArrayList<>();
        List<BigDecimal> sums = new ArrayList<>();
        for(Object item: list){
            Object[] arr = (Object[]) item;
            sums.add((BigDecimal) arr[0]);
            labels.add(((LocalDateTime) arr[1]).toLocalDate());
        }
        return new UnitPlotResponse(labels, sums);
    }
}
