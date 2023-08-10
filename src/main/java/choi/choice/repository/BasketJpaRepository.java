package choi.choice.repository;

import choi.choice.domain.Bsk;
import choi.choice.domain.Mbr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BasketJpaRepository implements BasketRepository{

    private final EntityManager em;

    @Autowired
    public BasketJpaRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void add(Bsk bsk) {
        em.persist(bsk);
    }

    @Override
    public void delete(String bskNo) {
        Bsk bsk = em.find(Bsk.class, bskNo);
        em.remove(bsk);
    }

    @Override
    public void deleteAll(String mbrNo) {
        String deleteQuery = "delete from bsk where mbr_no = :mbrNo";

        em.createQuery(deleteQuery, Bsk.class)
                .setParameter("mbrNo", mbrNo)
                .getResultList();
    }

    @Override
    public List<Bsk> findAll(Sort regDt) {
        return null;
    }
}
