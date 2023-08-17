package choi.choice.repository;

import choi.choice.domain.Good;
import choi.choice.domain.GoodReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<Good> findAll(Sort regDt){
        List<Good> goods = new ArrayList<>();
//        goods.add(em.find());
        return goods;
    }
    @Override
    public void deleteByNo(String goodNo) {
        Good good = em.find(Good.class, goodNo);
        em.remove(good);
    }

    @Override
    public Boolean existGood(String goodNo) {
        Good good = em.find(Good.class, goodNo);
        if (good == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public GoodReview findRvByNo(String goodNo) {
        GoodReview goodReview = em.find(GoodReview.class, goodNo);
        return goodReview;
    }
}
