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

    @Override
    public void saveGrd(MbrGrd mbrGrd) {
        em.persist(mbrGrd);
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
        //TODO
        String query = "select gr from GoodReview gr where 1=1 and gr.mbrNo = :mbrNo";

        List<GoodReview> reviews = em.createQuery(query, GoodReview.class)
                .setParameter("mbrNo", mbrNo)
                .getResultList();
        return reviews;
    }

    @Override
    public void addMtm(Mbr mbr) {
        //TODO
    }
}
