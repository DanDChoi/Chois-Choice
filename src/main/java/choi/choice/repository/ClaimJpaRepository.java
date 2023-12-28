package choi.choice.repository;

import choi.choice.domain.Clm;
import choi.choice.domain.ClmGood;

import javax.persistence.EntityManager;

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
        //TODO edtiClm
        String clmQuery = "update Clm c " +
                "set c.clmNo = :clmNo"
                ;
        String clmGoodQuery = "update ClmGood cg" +
                "set cg.clmNo = :clmNo"
                ;
    }
}
