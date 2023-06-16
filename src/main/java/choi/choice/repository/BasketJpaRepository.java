package choi.choice.repository;

import choi.choice.domain.Bsk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BasketJpaRepository implements BasketRepository{

    private final EntityManager em;

    @Autowired
    public BasketJpaRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void add(Bsk bsk) {
        em.persist(bsk);
    }

    @Override
    public void delete(String bskNo) {

    }

    @Override
    public Bsk findByNo(String bskNo) {
        return null;
    }
}
