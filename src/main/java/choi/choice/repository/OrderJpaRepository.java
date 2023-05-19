package choi.choice.repository;

import choi.choice.domain.Ord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class OrderJpaRepository implements OrderRepository{

    private final EntityManager em;

    @Autowired
    public OrderJpaRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void add(Ord ord) {
        em.persist(ord);
    }

}

