package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GoodRepository{

    void save(Good good);

    void saveGoodHist(Good good, String histNo);

    Good findByNo(String goodNo);

    List<Good> findAll(Sort regDt);

    List<GoodReview> findAllReviews(Sort regDt);

    void deleteByNo(String goodNo);

    void deleteGoodItmByNo(String goodNo);

    Boolean existGood(String goodNo);

    List<GoodReview> findRvByNo(String goodNo);

    List<Cpn> cpns();

    Cpn findCpnByNo(String cpnNo);

    List<Good> findGoodsByCate(String cate);

    List<Good> findBskGoods(Long mbrNo);

    void deleteBskGood(String goodNo);

    void updateGood(Good good);

    void insertGoodHist(GoodHist goodHist);

    String getGoodHistSeq(String goodNo);

    int addBukmk(Good good, Mbr mbr, int bukmkSn);

    int getBukmkSn(Mbr mbr);

    int getBukmkCnt(Mbr mbr);

    List<Good> bukmkGoods(Mbr mbr);
}
