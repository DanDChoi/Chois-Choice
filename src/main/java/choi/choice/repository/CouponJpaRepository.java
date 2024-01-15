package choi.choice.repository;

import choi.choice.domain.Cpn;
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
        int searchPeriod = Integer.parseInt(period);
        LocalDate date = LocalDate.now();
        date.minusDays(searchPeriod);

        String today = format.format(date);
        String query = "select c from Cpn c where 1=1 and c.cpnBegDt >= :today and c.cpnEndDt < :today";

        List<Cpn> cpns = em.createQuery(query, Cpn.class)
                .setParameter("today", today)
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
}
