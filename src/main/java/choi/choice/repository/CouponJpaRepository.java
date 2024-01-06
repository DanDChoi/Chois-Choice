package choi.choice.repository;

import choi.choice.domain.Cpn;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
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
