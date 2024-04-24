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
    public void addOrdGod(OrdGood ordGood) {
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
        String query = "select og from OrdGood og where og.ordNo = :ordNo";

        List<OrdGood> ordGoods = em.createQuery(query, OrdGood.class)
                .setParameter("ordNo", ordNo)
                .getResultList();

        return ordGoods;
    }
}

