package dvarubla.tracker.api;

import java.util.List;

public interface ProductService {
    List<Shop> getAllShops();
    CreatedID addShop(Shop shop);
}
