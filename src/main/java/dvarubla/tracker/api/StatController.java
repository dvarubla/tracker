package dvarubla.tracker.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/stat/")
public class StatController {
    private StatService _statService;
    StatController(StatService statService){
        _statService = statService;
    }

    @GetMapping(value = "total-money")
    Map<String, BigDecimal> getTotalMoney(){
        HashMap<String, BigDecimal> res = new HashMap<>();
        res.put("totalMoney", _statService.getTotalMoney());
        return res;
    }

    @GetMapping(value = "unit-plot")
    Map<String, UnitPlotResponse> getUnitPlot(String startDate, String endDate){
        HashMap<String, UnitPlotResponse> res = new HashMap<>();
        res.put("plot", _statService.getOnePlot(startDate, endDate));
        return res;
    }
}
