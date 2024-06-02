package choi.choice.repository;

import choi.choice.domain.Clm;
import choi.choice.domain.ClmGood;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClaimJpaRepository implements ClaimRepository {

    private EntityManager em;

    @Override
    public void addClm(Clm clm, ClmGood clmGood) {
      em.persist(clm);
      em.persist(clmGood);
    }

    @Override
    public Clm findClm(String clmNo) {
        String query = "select c from Clm c where c.clmNo = :clmNo";
        Clm findClm = em.createQuery(query, Clm.class)
                .setParameter("clmNo", clmNo)
                .getSingleResult();

        return findClm;
    }

    @Override
    public void editClm(Clm clm, ClmGood clmGood) {

        String clmQuery = "update Clm c " +
                "set c.clmNo = :clmNo" +
                ", mbrNo = :mbrNo" +
                ", udterId = :udterId" +
                ", udtDt = NOW()";
        String clmGoodQuery = "update ClmGood cg " +
                "set cg.clmGoodPK.clmNo = :clmNo" +
                ", udterId = :udterId" +
                ", udtDt = NOW()";

        Clm editClm = em.createQuery(clmQuery, Clm.class)
                .setParameter("clmNo", clm.getClmNo())
                .setParameter("mbrNo", clm.getMbrNo())
                .setParameter("udterId", "admin")
                .getSingleResult();

        ClmGood editClmGood = em.createQuery(clmGoodQuery, ClmGood.class)
                .setParameter("clmNo", clmGood.getClmGoodPK().getClmNo())
                .setParameter("udterId", "admin")
                .getSingleResult();
    }

    @Override
    public List<Clm> clmListByMbrNo(Long mbrNo) {
        String query = "select c from Clm c where c.mbrNo = :mbrNo";
        List<Clm> clms = em.createQuery(query, Clm.class)
                .setParameter("mbrNo", mbrNo)
                .getResultList();
        return clms;
    }

    @Override
    public List<Clm> clmListByOrdNo(String ordNo) {
        String query = "select c from Clm c where c.ordNo = :ordNo";
        List<Clm> clms = em.createQuery(query, Clm.class)
                .setParameter("ordNo", ordNo)
                .getResultList();
        return clms;
    }
}
