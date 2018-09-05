package dvarubla.tracker.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@RestController
@RequestMapping(value = "/api/shop")
public class ShopController {
    private ProductService _productService;

    ShopController(ProductService productService){
        _productService = productService;
    }

    @GetMapping
    public List<Shop> getAll(Optional<String> query, Optional<Integer> limit){
        if(query.isPresent()){
            if(limit.isPresent()){
                return _productService.findShopsByName(query.get(), limit.get());
            } else {
                return _productService.findShopsByName(query.get());
            }
        } else {
            return _productService.getAllShops();
        }
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
