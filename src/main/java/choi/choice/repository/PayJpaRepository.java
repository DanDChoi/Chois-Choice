package choi.choice.repository;

import choi.choice.domain.Pay;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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
    public List<Pay> findPays(Sort regDt) {
        return null;
    }

    @Override
    public void deletePay(String payNo) {
        Pay pay = em.find(Pay.class, payNo);
        em.remove(pay);
    }

    @Override
    public Pay findByNo(String payNo) {
        Pay pay = em.find(Pay.class, payNo);
        return pay;
    }
}