package dvarubla.tracker.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    private ProductService _productService;

    ProductController(ProductService productService){
        _productService = productService;
    }

    @GetMapping
    public List<Product> getAll(Optional<String> query, Optional<Integer> limit){
        if(query.isPresent()){
            if(limit.isPresent()){
                return _productService.findProductsByName(query.get(), limit.get());
            } else {
                return _productService.findProductsByName(query.get());
            }
        } else {
            return _productService.getAllProducts();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ID add(@RequestBody Product product){
        return _productService.addProduct(product);
    }

    @DeleteMapping
    public void delete(ID id){
        _productService.deleteProduct(id);
    }
}
