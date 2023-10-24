package choi.choice.service;

import choi.choice.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodService {

    void add(Good good, HttpServletRequest request);
    Good findByNo(String id);

    List<Good> findAll();
    void deleteByNo(String id, GoodHist goodHist);

    List<GoodReview> findRvByNo(String goodNo);

    List<Cpn> validCpns();

    List<Good> findGoodsByCate(String cate);

    List<Good> findBskGoods(Long mbrNo);

    void updateGood(Good good);

    int addBukmk(Good good, Mbr mbr);

    List<Good> findMbrBukmkGoods(Mbr mbr);
}
