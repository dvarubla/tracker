package dvarubla.tracker.api;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dvarubla.tracker.api.DateSerializer.DATE_FORMAT_PATTERN;

@Service
public class ProductServiceImpl implements ProductService {
    private static final int MAX_SEARCH_RESULTS = 6;
    @PersistenceContext
    private EntityManager _entityManager;

    private <T, TGen> List<T> getAllById(Class<T> itemClass, Class<TGen> genClass){
        CriteriaBuilder bld = _entityManager.getCriteriaBuilder();
        CriteriaQuery<T> crt = bld.createQuery(itemClass);
        Root<T> root = crt.from(itemClass);
        try {
            //noinspection unchecked
            crt.orderBy(bld.asc(root.get(
                    (SingularAttribute<T, Long>) genClass.getField("id").get(null)
            )));
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        return _entityManager.createQuery(crt).getResultList();
    }

    private <T, TGen> List<T> findByNameLike(Class<T> itemClass, Class<TGen> genClass, String name, int limit){
        String escName = name.replaceAll("[_%]", "");
        CriteriaBuilder bld = _entityManager.getCriteriaBuilder();
        CriteriaQuery<T> crt = bld.createQuery(itemClass);
        Root<T> root = crt.from(itemClass);
        crt.select(root);
        try {
            //noinspection unchecked
            crt.where(bld.like(bld.lower(root.get(
                    (SingularAttribute<T, String>) genClass.getField("name").get(null))),
                    "%" + escName.toLowerCase() + "%")
            );
            //noinspection unchecked
            crt.orderBy(bld.asc(root.get(
                    (SingularAttribute<T, Long>) genClass.getField("id").get(null)
            )));
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
        }
        TypedQuery<T> query = _entityManager.createQuery(crt);
        query.setFirstResult(0);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    private <T, TGen> Optional<T> findByUniqNameLock(Class<T> itemClass, Class<TGen> genClass, String name){
        CriteriaBuilder builder = _entityManager.getCriteriaBuilder();
        CriteriaQuery<T> crt = builder.createQuery(itemClass);
        Root<T> from = crt.from(itemClass);
        crt.select(from);
        try {
            //noinspection unchecked
            crt.where(builder.equal(from.get((SingularAttribute<T, String>) genClass.getField("name").get(null)), name));
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
        }
        TypedQuery<T> query = _entityManager.createQuery(crt);
        query.setLockMode(LockModeType.PESSIMISTIC_READ);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List<T> items = query.getResultList();
        if(items.size() == 0){
            return Optional.empty();
        } else {
            return Optional.of(items.get(0));
        }
    }

    @Override
    public List<Shop> getAllShops() {
        return getAllById(Shop.class, Shop_.class);
    }

    @Override
    public List<Shop> findShopsByName(String name) {
        return findShopsByName(name, MAX_SEARCH_RESULTS);
    }

    @Override
    public List<Shop> findShopsByName(String name, int limit) {
        return findByNameLike(Shop.class, Shop_.class, name, limit);
    }

    @Override
    @Transactional
    public ID addShop(Shop shop) {
        _entityManager.persist(shop);
        return new ID(shop.getId());
    }

    @Override
    @Transactional
    public void deleteShop(ID id){
        Shop shop = _entityManager.find(Shop.class, id.getId());
        _entityManager.remove(shop);
    }

    @Override
    public List<Product> getAllProducts() {
        return getAllById(Product.class, Product_.class);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return findProductsByName(name, MAX_SEARCH_RESULTS);
    }

    @Override
    public List<Product> findProductsByName(String name, int limit) {
        return findByNameLike(Product.class, Product_.class, name, limit);
    }

    @Override
    @Transactional
    public ID addProduct(Product product) {
        _entityManager.persist(product);
        return new ID(product.getId());
    }

    @Override
    @Transactional
    public void deleteProduct(ID id) {
        Product product = _entityManager.find(Product.class, id.getId());
        _entityManager.remove(product);
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return getAllById(Purchase.class, Purchase_.class);
    }

    private Optional<Shop> getShopByName(String name){
        return findByUniqNameLock(Shop.class, Shop_.class, name);
    }

    private Optional<Product> getProductByName(String name){
        return findByUniqNameLock(Product.class, Product_.class, name);
    }

    private Shop addShop(String name){
        Shop shop = new Shop();
        shop.setName(name);
        _entityManager.persist(shop);
        return shop;
    }

    private Product addProduct(String name){
        Product product = new Product();
        product.setName(name);
        _entityManager.persist(product);
        return product;
    }

    @Override
    @Transactional
    public List<ID> addPurchases(AddPurchasesRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        Optional<Shop> shopOpt = getShopByName(request.getShop());
        Shop shop = shopOpt.orElseGet(() -> addShop(request.getShop()));
        List<ID> list = new ArrayList<>(request.getPurchases().size());
        for(AddPurchasesRequest.Purchase p : request.getPurchases()){
            Optional<Product> productOpt = getProductByName(p.getProduct());
            Product product = productOpt.orElseGet(() -> addProduct(p.getProduct()));
            LocalDateTime date = LocalDateTime.parse(p.getPurchaseDate(), formatter);

            Purchase purchase = new Purchase();
            purchase.setPrice(p.getPrice());
            purchase.setCount(p.getCount());
            purchase.setPurchaseDate(date);
            purchase.setShop(shop);
            purchase.setProduct(product);
            _entityManager.persist(purchase);
            list.add(new ID(purchase.getId()));
        }
        return list;
    }
}
