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
}
