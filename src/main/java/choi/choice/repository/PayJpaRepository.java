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

    @Override
    public Pay findByOrdNo(String ordNo) {
        Pay pay = em.find(Pay.class, ordNo);
        return pay;
    }

    @Override
    public void addClmPay(Pay pay, String originPayNo) {
        String query = "INSERT INTO Pay " +
                "(payNo, payMnCd, payDt, payAmt, ordNo, regtrId, regDt, udterId, udtDt)" +
                "VALUES" +
                "(:payNo, :payMnCd, :payDt, :payAmt, :ordNo, :regtrId, :regDt, :udterId, :udtDt)";
        Pay clmPay = em.createQuery(query, Pay.class)
                .setParameter("payNo", pay.getPayNo())
                .setParameter("payMnCd", pay.getPayMnCd())
                .setParameter("payDt", pay.getPayDt())
                .setParameter("ordNo", pay.getOrdNo())
                .setParameter("regtrId", pay.getRegtrId())
                .setParameter("regDt", pay.getRegDt())
                .setParameter("udterId", pay.getUdterId())
                .setParameter("udtDt", pay.getUdtDt())
                .getSingleResult();
    }

    @Override
    public void editPay(String payNo, Pay pay) {
        String query = "UPDATE Pay p SET " +
                "p.payMnCd = :payMnCd" +
                ", p.udtDt = :udtDt " +
                ", p.udterId = :udterId " +
                "WHERE p.payNo = :payNo";
        em.createQuery(query)
                .setParameter("payMnCd", pay.getPayMnCd())
                .setParameter("udtDt", pay.getUdtDt())
                .setParameter("udterId", pay.getUdterId())
                .getSingleResult();
    }
}
