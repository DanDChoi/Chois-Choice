package choi.choice.repository;

import choi.choice.domain.Cpn;
import choi.choice.domain.CpnHist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Cpn> cpns(String period) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //period = 1, 7, 30 등등
        int searchPeriod = Integer.parseInt(period);

        LocalDate date = LocalDate.now(); //현재날짜
        LocalDate periodDate = LocalDate.now(); //현재날짜 - 파라미터 값
        periodDate.minusDays(searchPeriod);

        String today = format.format(date);
        String periodDt = format.format(periodDate);
        String query = "select c from Cpn c where 1=1 and c.cpnBegDt >= :periodDate and c.cpnEndDt < :today";

        List<Cpn> cpns = em.createQuery(query, Cpn.class)
                .setParameter("today", today)
                .setParameter("periodDate", periodDt)
                .getResultList();
        return cpns;
    }

    @Override
    public Cpn findCpnByNo(String cpnNo) {
        Cpn cpn = em.find(Cpn.class, cpnNo);
        return cpn;
    }

    @Override
    public void updateCpn(Cpn cpn) {
        String query = "update Cpn c " +
                "set c.cpnDcRate = :cpnDcRate" +
                ",c.cpnDcAmt = :cpnDcAmt" +
                ",c.cpnBegDt = :cpnBegDt" +
                ",c.cpnEndDt = :cpnEndDt" +
                ",c.udterId = :udterId" +
                ",c.udtDt = NOW()" +
                "where c.cpnNo = :cpnNo";
        Cpn updateCpn = em.createQuery(query, Cpn.class)
                .setParameter("cpnDcRate", cpn.getCpnDcRate())
                .setParameter("cpnBegDt", cpn.getCpnBegDt())
                .setParameter("cpnEndDt", cpn.getCpnEndDt())
                .setParameter("udterId", cpn.getUdterId())
                .getSingleResult();
    }

    @Override
    public void createCoupon(Cpn cpn) {
        em.persist(cpn);
    }

    @Override
    public void addCpnHist(CpnHist cpnHist) {
        em.persist(cpnHist);
    }

    @Override
    public int cpnHistTurn(String cpnNo) {
        String query = "select max(ch.cpnHistTurn) from CpnHist ch where ch.cpnNo = :cpnNo";
        int maxTurn = em.createQuery(query, int.class)
                .setParameter("cpnNo", cpnNo)
                .getSingleResult();
        return maxTurn;
    }

    @Override
    public void executeCpn(String cpnNo) {
        Cpn cpn = em.find(Cpn.class, cpnNo);
        em.remove(cpn);
    }

    @Override
    public CpnHist findMaxCpnTurnHistCpn(String cpnNo) {
        String query = "select ch from CpnHist cp where cp.cpnNo = :cpnNo order by cp.cpnHistTurn desc limit 1";
        CpnHist cpnHist = em.createQuery(query, CpnHist.class)
                .setParameter("cpnNo", cpnNo)
                .getSingleResult();
        return cpnHist;
    }
}
