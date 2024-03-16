package choi.choice.repository;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AdminJpaRepository implements  AdminRepository{

    private final EntityManager em;

    public AdminJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addStdCtgry(StdCtgry stdCtgry) {
        em.persist(stdCtgry);
    }

    @Override
    public void addDspCtgry(DspCtgry dspCtgry) {
        em.persist(dspCtgry);
    }
}
