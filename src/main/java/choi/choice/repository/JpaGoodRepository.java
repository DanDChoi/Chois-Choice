package choi.choice.repository;

import choi.choice.domain.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JpaGoodRepository implements GoodRepository{

    private final EntityManager em;

    @Autowired
    public JpaGoodRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Good good) {
        em.persist(good);
    }

    @Override
    public Good findByNo(String goodNo) {
        Good good = em.find(Good.class, goodNo);
        return good;
    }

    @Override
    public void deleteByNo(String goodNo) {
    }
}