package dvarubla.tracker.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shop")
public class ShopController {
    private ProductService _productService;

    ShopController(ProductService productService){
        _productService = productService;
    }

    @GetMapping
    public List<Shop> getAll(){
        return _productService.getAllShops();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ID add(@RequestBody Shop shop){
        return _productService.addShop(shop);
    }

    @DeleteMapping
    public void delete(ID id){
        _productService.deleteShop(id);
    }
}
