package choi.choice.repository;

import choi.choice.domain.*;
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
        String query = "update StdCtgry sc " +
                "set sc.stdCtgryNm = :stdCtgryNm " +
                ", sc.useYn = :useYn " +
                ", sc.deleteYn = :deleteYn " +
                ", sc.leafCtgryYn = :leafCtgryYn " +
                ", sc.prdlstGrpCd = :prdlstGrpCd " +
                ", sc.godEvlGrpCd = :godEvlGrpCd " +
                ", sc.udtDt = :udtDt " +
                ", sc.udterId = :udterId " +
                "where sc.stdCtgryNo = :stdCtgryNo";
        StdCtgry updateStdCtgry = em.createQuery(query, StdCtgry.class)
                .setParameter("stdCtgryNm", stdCtgry.getStdCtgryNm())
                .setParameter("useYn", stdCtgry.getUseYn())
                .setParameter("deleteYn", stdCtgry.getDeleteYn())
                .setParameter("leafCtgryYn", stdCtgry.getLeafCtgryYn())
                .setParameter("prdlstGrpCd", stdCtgry.getPrdlstGrpCd())
                .setParameter("godEvlGrpCd", stdCtgry.getGodEvlGrpCd())
                .setParameter("udtDt", stdCtgry.getUdtDt())
                .setParameter("udterId", stdCtgry.getUdterId())
                .getSingleResult();

    }

    @Override
    public void updateDspCtgry(DspCtgry dspCtgry) {
        String query = "update DspCtgry dc " +
                        "set dc.dspCtgryNm = :dspCtgryNm " +
                        ", dc.ctgryDpthNo = :ctgryDpthNo " +
                        ", dc.dspYn = :dspYn " +
                        ", dc.deleteYn = :deleteYn " +
                        ", dc.leafCtgryYn = :leafCtgryYn " +
                        ", dc.udtDt = :udtDt " +
                        ", dc.udterId = :udterId " +
                        "where dc.dspCtgryNo = :dspCtgryNo";
        DspCtgry updateDspCtgry = em.createQuery(query, DspCtgry.class)
                .setParameter("dspCtgryNm", dspCtgry.getDspCtgryNm())
                .setParameter("ctgryDpthNo", dspCtgry.getDspCtgryNo())
                .setParameter("dspYn", dspCtgry.getDspYn())
                .setParameter("deleteYn", dspCtgry.getDeleteYn())
                .setParameter("udtDt", dspCtgry.getUdtDt())
                .setParameter("udterId", dspCtgry.getUdterId())
                .setParameter("dspCtgryNo", dspCtgry.getDspCtgryNo())
                .getSingleResult();
    }

    @Override
    public void addStdDspCtgryCnnc(StdDspCtgryCnnc stdDspCtgryCnnc) {
        em.persist(stdDspCtgryCnnc);
    }

    @Override
    public int stdDspCtgryDupChk(String stdCtgryNo, String dspCtgryNo) {
        String query = "SELECT count(1) FROM StdDspCtgryCnnc sdcc " +
                "WHERE sdcc.stdCtgryNo = :stdCtgryNo " +
                "AND sdcc.dspCtgryNo = :dspCtgryNo";
        int count = em.createQuery(query, Integer.class)
                .setParameter("stdCtgryNo", stdCtgryNo)
                .setParameter("dspCtgryNo", dspCtgryNo)
                .getSingleResult();
        return count;
    }

    @Override
    public void stdDspCtgrtCnncDelete(StdDspCtgryCnnc stdDspCtgryCnnc, StdCtgry stdCtgry, DspCtgry dspCtgry) {
        String query = "DELETE FROM StdDspCtgryCnnc sdcc " +
                "WHERE sdcc.stdCtgryNo = :stdCtgryNo " +
                "AND sdcc.dspCtgryNo = :dspCtgryNo";
        em.createQuery(query)
                .setParameter("stdCtgryNo", stdCtgry.getStdCtgryNo())
                .setParameter("dspCtgryNo", dspCtgry.getDspCtgryNo())
                .getSingleResult();
    }

    @Override
    public List<SysWordDic> getSysWordDicList() {
        String query = "SELECT swd FROM SysWordDic swd";
        List<SysWordDic> list = em.createQuery(query, SysWordDic.class)
                .getResultList();
        return list;
    }

    @Override
    public void addSysCd(SysCd sysCd) {
        em.persist(sysCd);
    }
}
