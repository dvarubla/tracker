package dvarubla.tracker.api;

import java.math.BigDecimal;

public interface StatService {
    BigDecimal getTotalMoney();
    UnitPlotResponse getOnePlot(String startDateStr, String endDateStr);
}
