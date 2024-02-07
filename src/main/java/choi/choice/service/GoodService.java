package choi.choice.service;

import choi.choice.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodService {

    void add(Good good, HttpServletRequest request);
    Good findByNo(String goodNo);

    GoodItm findGoodItmByNo(String goodNo);

    List<Good> findAll();

    List<GoodReview> findAllReviews();

    void updateReview(GoodReview review);

    void deleteReview(String goodNo);
    void deleteByNo(String id, GoodHist goodHist);

    void deleteGoodItmByNo(String goodNo);

    List<GoodReview> findRvByNo(String goodNo);

    List<Good> findGoodsByCate(String cate);

    List<Good> findBskGoods(Long mbrNo);

    void updateGood(Good good);

    int addBukmk(Good good, Mbr mbr);

    List<Good> findMbrBukmkGoods(Mbr mbr);
}
