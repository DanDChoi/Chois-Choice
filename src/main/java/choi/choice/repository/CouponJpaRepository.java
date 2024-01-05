package choi.choice.repository;

import choi.choice.domain.Cpn;

import javax.persistence.EntityManager;

public class CouponJpaRepository implements CouponRepository{

    private EntityManager em;

    @Override
    public Cpn cpnDetail(String cpnNo) {
        String query = "select c from Cpn c where c.cpnNo = :cpnNo";
        Cpn cpn = em.createQuery(query, Cpn.class)
                .setParameter("cpnNo", cpnNo)
                .getSingleResult();
        return cpn;
    }
}
