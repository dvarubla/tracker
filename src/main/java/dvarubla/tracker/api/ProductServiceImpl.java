package dvarubla.tracker.api;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @PersistenceContext
    private EntityManager _entityManager;

    @Override
    public List<Shop> getAllShops() {
        CriteriaQuery<Shop> crt =_entityManager.getCriteriaBuilder().createQuery(Shop.class);
        crt.from(Shop.class);
        return _entityManager.createQuery(crt).getResultList();
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
}
