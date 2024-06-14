package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class MbrJpaRepository implements MbrRepository {

    private final EntityManager em;

    @Autowired
    public MbrJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Mbr mbr) {
        em.persist(mbr);
    }

//    @Override
//    public void saveMbrHist(MbrHist mbrHist) {
//        em.persist(mbrHist);
//    }

    @Override
    public void saveGrd(MbrGrd mbrGrd) {
        em.persist(mbrGrd);
    }

//    @Override
//    public void saveGrdHist(MbrGrdHist mbrGrdHist) {
//        em.persist(mbrGrdHist);
//    }

    @Override
    public void updateMbr(Mbr mbr) {
        String query = "UPDATE Mbr m " +
                "SET m.mbrPwd = :mbrPwd" +
                ", m.mobilNo = :mobilNo " +
                "WHERE m.mbrNo = :mbrNo";
        Mbr updateMbr = em.createQuery(query, Mbr.class)
                .setParameter("mbrPwd", mbr.getMbrPwd())
                .setParameter("mobilNo", mbr.getMobilNo())
                .setParameter("mbrNo", mbr.getMbrNo())
                .getSingleResult();
    }

    @Override
    public Mbr findById(String id) {
        Mbr mbr = em.find(Mbr.class, id);
        em.persist(mbr);
        return mbr;
    }

    @Override
    public Mbr findByEmail(String email) {
        Mbr mbr = em.find(Mbr.class, email);
        em.persist(mbr);
        return mbr;
    }

    @Override
    @Transactional
    public MbrGrd findGrdByNo(Long mbrNo) {
        MbrGrd mbrGrd = em.find(MbrGrd.class, mbrNo);
        em.persist(mbrGrd);
        return mbrGrd;
    }

    @Override
    public List<Mbr> findAll(Sort regDt) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        Mbr mbr = em.find(Mbr.class, id);
        em.remove(mbr);
    }

    @Override
    public Boolean existMbr(String id) {
        Mbr mbr = em.find(Mbr.class, id);

        if (mbr == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Mbr> findMbrByGrd(String grd) {
        String query = "SELECT m FROM Mbr m JOIN MbrGrd mg ON m.mbrNo = mg.mbrNo WHERE mg.mbrGrd = :grd";

        List<Mbr> mbrs = em.createQuery(query, Mbr.class)
                .setParameter("grd", grd)
                .getResultList();
        return mbrs;
    }

    @Override
    public List<MbrCpn> mbrIsuCpn(Long mbrNo) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = format.format(date);
        String query = "select mc from MbrCpn mc where 1=1 and mc.mbrNo = :mbrNo and mc.cpnBegDt >= :today and mc.cpnEndDt < :today";

        List<MbrCpn> cpns = em.createQuery(query, MbrCpn.class)
                .setParameter("mbrNo", mbrNo)
                .setParameter("today", today)
                .getResultList();
        return cpns;
    }

    @Override
    public List<GoodReview> findAllReviews(Long mbrNo) {
        String query = "select gr from GoodReview gr where 1=1 and gr.mbrNo = :mbrNo";

        List<GoodReview> reviews = em.createQuery(query, GoodReview.class)
                .setParameter("mbrNo", mbrNo)
                .getResultList();
        return reviews;
    }

    @Override
    public void addMtm(Mbr mbr, CsoMtmInq csoMtmInq) {
       em.persist(CsoMtmInq.class);
    }

    @Override
    public void saveMbrLoginLog(MbrLoginLog mbrLoginLog) {
        em.persist(mbrLoginLog);
    }

    @Override
    public void addLoginFailrCount(Mbr mbr) {
        String query = "update Mbr m set" +
                " m.mbrLoginLastFailrCount = (select m1.mbrLoginLastFailrCount + 1 from Mbr m1 where m1.mbrNo = :mbrNo)" +
                " , m.udterId = :udterId" +
                " , m.udtDt = NOW()" +
                " where m.mbrNo = :mbrNo";
        Mbr updatedMbr = em.createQuery(query, Mbr.class)
                .setParameter("mbrNo", mbr.getMbrNo())
                .setParameter("udterId", mbr.getMbrNo())
                .getSingleResult();
    }

    @Override
    public Integer getLoginFailrCount(Mbr mbr) {
        String query = "select m.mbrLoginLastFailrCount from Mbr m where m.mbrNo = :mbrNo";

        Integer loginFailrCount = em.createQuery(query, Mbr.class)
                .setParameter("mbrNo", mbr.getMbrNo())
                .getSingleResult().getMbrLoginLastFailrCount();

        return loginFailrCount;
    }

    @Override
    public void loginFailrCountReset(Mbr mbr, Integer count) {
        String query = "update Mbr m set m.mbrLoginLastFailrCount = :count" +
                " , m.udterId = :mbrNo" +
                " , m.udtDt = NOW()" +
                " where m.mbrNo = :mbrNo";

        em.createQuery(query, Mbr.class)
                .setParameter("count", count)
                .setParameter("mbrNo", mbr.getMbrNo())
                .getSingleResult();
    }
}
