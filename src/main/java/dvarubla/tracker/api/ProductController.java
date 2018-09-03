package dvarubla.tracker.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    private ProductService _productService;

    ProductController(ProductService productService){
        _productService = productService;
    }

    @GetMapping
    public List<Product> getAll(){
        return _productService.getAllProducts();
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
