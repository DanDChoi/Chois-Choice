package choi.choice.repository;

import choi.choice.domain.Pay;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PayJpaRepository implements PayRepository {

    private final EntityManager em;

    public PayJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Pay pay) {
        em.persist(pay);
    }

    @Override
    public Pay findByNo(String payNo) {
        Pay pay = em.find(Pay.class, payNo);
        return pay;
    }
}
