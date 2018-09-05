package dvarubla.tracker.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/purchase")

public class PurchaseController {
    private ProductService _productService;

    PurchaseController(ProductService productService){
        _productService = productService;
    }

    @GetMapping
    public List<Purchase> getAll(){
        return _productService.getAllPurchases();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ID> add(@RequestBody AddPurchasesRequest request){
        return _productService.addPurchases(request);
    }
}
