package choi.choice.repository;

import choi.choice.domain.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GoodJpaRepository implements GoodRepository{

    private final EntityManager em;

    @Autowired
    public GoodJpaRepository(EntityManager em) {
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
    public List<Good> findAll(){
        List<Good> goods = new ArrayList<>();
//        goods.add(em.find());
        return goods;
    }
    @Override
    public void deleteByNo(String goodNo) {
    }
}
