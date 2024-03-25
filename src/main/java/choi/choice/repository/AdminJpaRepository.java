package choi.choice.repository;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public List<StdCtgry> stdCtgryList() {
        String query = "SELECT sc FROM StdCtgry sc";
        List<StdCtgry> stdCtgries = em.createQuery(query, StdCtgry.class)
                .getResultList();
        return stdCtgries;
    }

    @Override
    public List<DspCtgry> dspCtgryList() {
        String query = "SELECT dc FROM DspCtgry dc";
        List<DspCtgry> dspCtgries = em.createQuery(query, DspCtgry.class)
                .getResultList();
        return dspCtgries;
    }

    @Override
    public void stdCtgryDelete(String stdCtgryNo) {
        StdCtgry stdCtgry = em.find(StdCtgry.class, stdCtgryNo);
        em.remove(stdCtgry);
    }

    @Override
    public void dspCtgryDelete(String dspCtgryNo) {
        DspCtgry dspCtgry = em.find(DspCtgry.class, dspCtgryNo);
        em.remove(dspCtgry);
    }

    @Override
    public void updateStdCtgry(StdCtgry stdCtgry) {
        //TODO
    }

    @Override
    public void updateDspCtgry(DspCtgry dspCtgry) {

    }
}
