package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GoodRepository{

    void save(Good good);

    void saveGoodHist(Good good, String histNo);

    Good findByNo(String goodNo);

    List<Good> findAll(Sort regDt);

    void deleteByNo(String goodNo);

    Boolean existGood(String goodNo);

    List<GoodReview> findRvByNo(String goodNo);

    List<Cpn> cpns();

    List<Good> findGoodsByCate(String cate);

    List<Good> findBskGoods(Long mbrNo);

    void deleteBskGood(String goodNo);

    void updateGood(Good good);

    void insertGoodHist(GoodHist goodHist);

    String getGoodHistSeq(String goodNo);

    int addBukmk(Good good, int bukmkSn);

    int getBukmkSn(Mbr mbr);
}
