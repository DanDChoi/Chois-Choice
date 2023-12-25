package choi.choice.repository;

import choi.choice.domain.Clm;
import choi.choice.domain.ClmGood;

import javax.persistence.EntityManager;

public class ClaimJpaRepository implements ClaimRepository {

    private EntityManager em;
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
    }
}
