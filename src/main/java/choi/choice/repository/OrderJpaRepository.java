package choi.choice.repository;

import choi.choice.domain.Ord;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void add(Ord ord) {
        em.persist(ord);
    }

    @Override
    public Ord findOne(String ordNo) {
        return null;
    }

    @Override
    public List<Ord> findAll() {
        return null;
    }
}

