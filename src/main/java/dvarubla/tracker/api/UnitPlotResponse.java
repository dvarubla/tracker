package dvarubla.tracker.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
class UnitPlotResponse {
    @Getter
    @Setter
    private List<LocalDate> labels;
    @Getter
    @Setter
    private List<BigDecimal> money;
}
