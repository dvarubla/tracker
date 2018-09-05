package dvarubla.tracker.api;

import java.util.List;

public interface ProductService {
    List<Shop> getAllShops();
    List<Shop> findShopsByName(String name);
    List<Shop> findShopsByName(String name, int limit);
    ID addShop(Shop shop);
    void deleteShop(ID id);

    List<Product> getAllProducts();
    ID addProduct(Product product);
    void deleteProduct(ID id);

    List<Purchase> getAllPurchases();
    List<ID> addPurchases(AddPurchasesRequest request);
}
