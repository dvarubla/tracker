package dvarubla.tracker.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/api/all")
public class AllController {
    @PersistenceContext
    private EntityManager _entityManager;

    @DeleteMapping
    @Transactional
    public void delete(){
        _entityManager.createStoredProcedureQuery("delete_all").execute();
    }
}
