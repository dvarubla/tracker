package dvarubla.tracker.api;

import java.util.List;

public interface ProductService {
    List<Shop> getAllShops();
    ID addShop(Shop shop);
    void deleteShop(ID id);
}
