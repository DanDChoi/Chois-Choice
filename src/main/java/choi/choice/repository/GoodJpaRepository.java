package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class GoodJpaRepository implements GoodRepository{

    private final EntityManager em;

    @Autowired
    public GoodJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(GoodExtend goodExtend) {
        em.persist(goodExtend.getGood());
        em.persist(goodExtend.getGoodImgList());
    }

    @Override
    public void saveItm(GoodItm goodItm) {
        em.persist(goodItm);
    }

    @Override
    public void saveGoodHist(Good good, String histNo) {
        String query = "insert into GoodHist " +
                "(goodHistPK, goodNm, histDt, saleBegDate, saleEndDate, colorNm, colorCd, regtrId, regDt, udterId, udtDt) values "+
                "(:goodHistPK, :goodNm, NOW(), :saleBegDate, :saleEndDate, :colorNm, :colorCd, :regtrId, :regDt, :udterId, :udtDt)";
        GoodHist goodHist = em.createQuery(query, GoodHist.class)
                .setParameter("goodHistPK", new GoodHistPK(good.getGoodNo(), +1))
                .setParameter("goodNm", good.getGoodNm())
                .setParameter("saleBegDate", good.getSaleBegDate())
                .setParameter("saleEndDate", good.getSaleEndDate())
                .setParameter("colorNm", good.getColorNm())
                .setParameter("colorCd", good.getColorCd())
                .setParameter("regtrId", good.getRegtrId())
                .setParameter("regDt", good.getRegDt())
                .setParameter("udterId", good.getUdterId())
                .setParameter("udtDt", good.getUdtDt())
                .getSingleResult();
    }

    @Override
    public Good findByNo(String goodNo) {
        Good good = em.find(Good.class, goodNo);
        return good;
    }

    @Override
    public GoodItm findGoodItmByNo(String goodNo) {
        GoodItm goodItm = em.find(GoodItm.class, goodNo);
        return goodItm;
    }

    @Override
    public List<Good> findAll(Sort regDt){
        String query = "SELECT g FROM Good g";

        List<Good> goods = em.createQuery(query, Good.class)
                .getResultList();
        return goods;
    }

    @Override
    public List<GoodReview> findAllReviews(Sort regDt) {
        String query = "SELECT r FROM GoodReview r";
        List<GoodReview> reviews = em.createQuery(query, GoodReview.class)
                .getResultList();
        return reviews;
    }

    @Override
    public void updateReview(GoodReview review) {
        String query = "update GoodReview gr " +
                "set gr.reviewCont = :reviewCont " +
                ",gr.bestYn = :bestYn " +
                ",gr.dspYn = :dspYn " +
                ",gr.udterId = :udterId " +
                ",gr.udtDt = now() " +
                "where gr.reviewSn = :reviewSn";
        GoodReview updateGoodReview = em.createQuery(query, GoodReview.class)
                .setParameter("reviewCont", review.getReviewCont())
                .setParameter("bestYn", review.getBestYn())
                .setParameter("dspYn", review.getDspYn())
                .setParameter("udterId", review.getUdterId())
                .setParameter("reviewSn", review.getReviewSn())
                .getSingleResult();
    }

    @Override
    public void deleteReview(String goodNo) {
        GoodReview goodReview = em.find(GoodReview.class, goodNo);
        em.remove(goodReview);
    }

    @Override
    public void deleteByNo(String goodNo) {
        Good good = em.find(Good.class, goodNo);
        em.remove(good);
    }

    @Override
    public void deleteGoodItmByNo(String goodNo) {
        GoodItm goodItm = em.find(GoodItm.class, goodNo);
        em.remove(goodItm);
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
    public Boolean existGoodItm(String goodNo) {
        GoodItm goodItm = em.find(GoodItm.class, goodNo);
        if (goodItm == null) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public List<GoodReview> findRvByNo(String goodNo) {
        List<GoodReview> goodReview = Collections.singletonList(em.find(GoodReview.class, goodNo));
        return goodReview;
    }

    @Override
    public List<Good> findGoodsByCate(String cate) {
        String query = "select g from Good g where 1=1 and g.stdCtgryNo = :cate";
        List<Good> goods = em.createQuery(query, Good.class)
                .setParameter("cate", cate)
                .getResultList();
        return goods;
    }

    @Override
    public List<Good> findBskGoods(Long mbrNo) {
        String query = "select g from BskGood bg join Good g on bg.goodNo = g.goodNo join Bsk b on b.bskNo = bg.bskNo where 1=1 and b.mbrNo = :mbrNo";
        List<Good> bskGoods = em.createQuery(query, Good.class)
                .setParameter("mbrNo", mbrNo)
                .getResultList();
        return bskGoods;
    }

    @Override
    public void deleteBskGood(String goodNo) {
        String query = "delete BskGood b where b.goodNo = :goodNo";
        Bsk bsk = em.createQuery(query, Bsk.class)
                .setParameter("goodNo", goodNo)
                .getSingleResult();
    }

    @Override
    public void insertGoodHist(GoodHist goodHist) {
        GoodHistPK goodHistPK = new GoodHistPK();
        goodHistPK.setGoodHistNo(goodHistPK.getGoodHistNo());
        goodHistPK.setGoodNo(goodHistPK.getGoodNo());
        String query = "insert into GoodHist " +
                "(" +
                "goodHistPK" +
                ",goodNm" +
                ",histDt" +
                ",saleBegDate" +
                ",saleEndDate" +
                ",colorNm" +
                ",colorCd" +
                ",regtrId" +
                ",regDt" +
                ",udterId" +
                ",udtDt" +
                ")" +
                "values" +
                "(:goodHistPK, :goodNm, NOW(), :saleBegDt, :saleEndDt, :colorNm, :colorCd, :regtrId, :regDt, :udterId, :udtDt)";
        GoodHist goodHist1 = em.createQuery(query, GoodHist.class)
                .setParameter("goodHistPK", goodHistPK)
                .setParameter("goodNm", goodHist.getGoodNm())
                .setParameter("saleBegDt", goodHist.getSaleBegDate())
                .setParameter("saleEndDt", goodHist.getSaleEndDate())
                .setParameter("colorNm", goodHist.getColorNm())
                .setParameter("colorCd", goodHist.getColorCd())
                .setParameter("regtrId", goodHist.getRegtrId())
                .setParameter("regDt", goodHist.getRegDt())
                .setParameter("udterId", goodHist.getUdterId())
                .setParameter("udtDt", goodHist.getUdtDt())
                .getSingleResult();
    }

    @Override
    public void updateGood(Good good) {
        String query = "update Good g " +
                        "set g.goodNm = :goodNm " +
                            ",g.goodPrc = :goodPrc " +
                            ",g.saleBegDate = :saleBegDate " +
                            ",g.saleEndDate = :saleEndDate " +
                            ",g.udterId = :udterId " +
                            ",g.udtDt = now() " +
                        "where g.goodNo = :goodNo";
        Good updateGood = em.createQuery(query, Good.class)
                .setParameter("goodNm", good.getGoodNm())
                .setParameter("goodPrc", good.getGoodPrc())
                .setParameter("saleBegDate", good.getSaleBegDate())
                .setParameter("saleEndDate", good.getSaleEndDate())
                .setParameter("udterId", good.getUdterId())
                .setParameter("goodNo", good.getGoodNo())
                .getSingleResult();
    }

    @Override
    public String getGoodHistSeq(String goodNo) {
        String query = "select max(gh.goodHistPK.goodHistNo) from GoodHist gh where gh.goodHistPK.goodNo = :goodNo order by gh.goodHistPK.goodHistNo desc limit 1";
        String goodHistSeq = String.valueOf(em.createQuery(query, Good.class)
                .setParameter("goodNo", goodNo)
                .getSingleResult());
        return goodHistSeq;
    }

    @Override
    public int getBukmkSn(Mbr mbr) {
        String query = "select max(mb.bukmkSn) from MbrBukmk mb where mb.mbrNo = :mbrNo order by mb.bukmkSn desc limit 1";
        int bukmkSn = em.createQuery(query, int.class)
                .setParameter("mbrNo", mbr.getMbrNo())
                .getSingleResult();
        return bukmkSn;
    }

    @Override
    public int addBukmk(Good good, Mbr mbr, int bukmkSn) {
        String query = "INSERT INTO MbrBukmk (bukmkSn, mbrNo, goodNo, regtrId, regDt, udterId, udtDt) VALUES (:bukmkSn, :mbrNo, :goodNo, :regtrId, now(), :udterId, now())";
        MbrBukmk mbrBukmk = em.createQuery(query, MbrBukmk.class)
                .setParameter("bukmkSn", bukmkSn)
                .setParameter("mbrNo",mbr.getMbrNo())
                .setParameter("goodNo", good.getGoodNo())
                .setParameter("regtrId",mbr.getMbrId())
                .setParameter("udterId",mbr.getMbrId())
                .getSingleResult();

        return getBukmkCnt(mbr);
    }

    @Override
    public int getBukmkCnt(Mbr mbr) {
        String query2 = "select count(1) from MbrBukmk mb where mb.mbrNo = :mbrNo";
        int mbrBukmkCnt = em.createQuery(query2, int.class)
                .setParameter("mbrNo", mbr.getMbrNo())
                .getSingleResult();
        return mbrBukmkCnt;
    }

    @Override
    public List<Good> bukmkGoods(Mbr mbr) {
        String query = "select g from Good as g join MbrBukmk mb on g.goodNo = mb.goodNo where mb.mbrNo = :mbrNo";
        List<Good> bukmkGoods = em.createQuery(query, Good.class)
                .setParameter("mbrNo", mbr.getMbrNo())
                .getResultList();
        return bukmkGoods;
    }
}
