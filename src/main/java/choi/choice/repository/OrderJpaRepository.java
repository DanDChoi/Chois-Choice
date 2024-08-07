package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderJpaRepository implements OrderRepository{

    private final EntityManager em;

    @Autowired
    public OrderJpaRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void addOrd(Ord ord) {
        em.persist(ord);
    }

    @Override
    public void addOrdGood(OrdGood ordGood) {
        em.persist(ordGood);
    }

    @Override
    public void addPay(Pay pay) {
        em.persist(pay);
    }

    @Override
    public void addLgsDlv(LgsDlv lgsDlv) {
        em.persist(lgsDlv);
    }

    @Override
    public void addLgsDlvsp(LgsDlvsp lgsDlvsp) {
        em.persist(lgsDlvsp);
    }

    @Override
    public void addLgsDlivyDrctGood(LgsDlivyDrctGood lgsDlivyDrctGood) {
        em.persist(lgsDlivyDrctGood);
    }

    @Override
    public Ord findOne(String ordNo) {
        Ord ord = em.find(Ord.class, ordNo);
        em.persist(ord);
        return ord;
    }

    @Override
    public List<Ord> findAll(Sort regDt) {
        return null;
    }

    @Override
    public void deleteByOrdNo(String ordNo) {
        Ord ord = em.find(Ord.class, ordNo);
        em.remove(ord);
    }

    @Override
    public Boolean existOrd(String ordNo) {
        Ord ord = em.find(Ord.class, ordNo);
        if (ord == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public List<Ord> findDrctOrd(String status) {
        String query = "select o from Ord o where o.ordStatus = :status";

        List<Ord> ords = em.createQuery(query, Ord.class)
                        .setParameter("status", status)
                        .getResultList();
        return ords;

    }

    @Override
    public List<Ord> findOrdsByMbrNo(Long mbrNo) {
        String query = "select o from Ord o where o.mbrNo = :mbrNo";

        List<Ord> ords = em.createQuery(query, Ord.class)
                        .setParameter("mbrNo", mbrNo)
                        .getResultList();
        return ords;
    }

    @Override
    public List<OrdGood> findBestGoods() {
        String query = "select og.goodNo, count(1) from OrdGood og group by og.goodNo order by count(1) desc limit 10";
        List<OrdGood> bestGoods = em.createQuery(query, OrdGood.class)
                .getResultList();
        return bestGoods;
    }

    @Override
    public List<OrdGood> findOrdGoods(String ordNo) {
        String query = "select og from OrdGood og where og.ordGoodPK.ordNo = :ordNo";

        List<OrdGood> ordGoods = em.createQuery(query, OrdGood.class)
                .setParameter("ordNo", ordNo)
                .getResultList();

        return ordGoods;
    }

    @Override
    public Pay findPayByOrdNo(String ordNo) {
        String query = "select p from Pay p where p.ordNo = :ordNo";

        Pay pay = em.createQuery(query, Pay.class)
                .setParameter("ordNo", ordNo)
                .getSingleResult();
        return pay;
    }

    @Override
    public List<PayRfd> findPayRfdByPayNo(String payNo) {
        String query = "select pr from PayRfd pr where pr.payNo = :payNo";

        List<PayRfd> payRfds = em.createQuery(query, PayRfd.class)
                .setParameter("payNo", payNo)
                .getResultList();
        return payRfds;
        return null;
    }
}

