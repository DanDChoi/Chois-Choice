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
    public List<Cpn> cpns() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = format.format(date);
        String query = "select c from Cpn c where 1=1 and c.cpnBegDt >= :today and c.cpnEndDt < :today";

        List<Cpn> cpns = em.createQuery(query, Cpn.class)
                .setParameter("today", today)
                .getResultList();
        return cpns;
    }

    @Override
    public List<GoodReview> findRvByNo(String goodNo) {
        List<GoodReview> goodReview = Collections.singletonList(em.find(GoodReview.class, goodNo));
        return goodReview;
    }

    @Override
    public List<Good> findGoodsByCate(String cate) {
        String query = "select g from Good g where 1=1 and g.category = :cate";
        List<Good> goods = em.createQuery(query, Good.class)
                .setParameter("cate", cate)
                .getResultList();
        return goods;
    }

    @Override
    public List<Good> findBskGoods(Long mbrNo) {
        String query = "select g from Bsk b join Good g on b.goodNo = g.goodNo where 1=1 and b.mbrNo = :mbrNo";
        List<Good> bskGoods = em.createQuery(query, Good.class)
                .setParameter("mbrNo", mbrNo)
                .getResultList();
        return bskGoods;
    }

    @Override
    public void deleteBskGood(String goodNo) {
        String query = "delete Bsk b where b.goodNo = :goodNo";
        Bsk bsk = em.createQuery(query, Bsk.class)
                .setParameter("goodNo", goodNo)
                .getSingleResult();
    }

    @Override
    public void insertGoodHist(GoodHist goodHist) {
        String query = "insert into GoodHist gh" +
                "(" +
                "gh.goodNo" +
                ",gh.goodNm" +
                ",gh.goodHistNo" +
                ",gh.histDt" +
                ",gh.saleBegDt" +
                ",gh.saleEndDt" +
                ",gh.colorNm" +
                ",gh.colorCd" +
                ",gh.regtrId" +
                ",gh.regDt" +
                ",gh.udterId" +
                ",gh.udtDt" +
                ")" +
                "values" +
                "(" +
                ":goodNo, :goodNm, :goodHistNo, :histDt, :saleBegDt, :saleEndDt, :colorNm, :colorCd, :regtrId, :regDt, :udterId, :udtDt";
        GoodHist goodHist1 = em.createQuery(query, GoodHist.class)
                .setParameter("goodNo", goodHist.getGoodNo())
                .setParameter("goodNm", goodHist.getGoodNm())
//                .setParameter("goodHistNo", )
//                .setParameter("histDt",)
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
        String query = "select max(gh.goodHistNo) from GoodHist gh where gh.goodNo = :goodNo order by gh.goodHistNo desc limit 1";
        String goodHistSeq = String.valueOf(em.createQuery(query, Good.class)
                .setParameter("goodNo", goodNo)
                .getSingleResult());
        return goodHistSeq;
    }
}
