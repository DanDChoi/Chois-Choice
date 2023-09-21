package choi.choice.repository;

import choi.choice.domain.Cpn;
import choi.choice.domain.Good;
import choi.choice.domain.GoodReview;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GoodRepository{

    void save(Good good);

    Good findByNo(String goodNo);

    List<Good> findAll(Sort regDt);

    void deleteByNo(String goodNo);

    Boolean existGood(String goodNo);

    GoodReview findRvByNo(String goodNo);

    List<Cpn> cpns();

    List<Good> findGoodsByCate(String cate);

    List<Good> findBskGoods(Long mbrNo);
}
