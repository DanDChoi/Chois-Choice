package choi.choice.repository;

import choi.choice.domain.Cpn;
import choi.choice.domain.CpnHist;

import java.util.List;

public interface CouponRepository {

    Cpn cpnDetail(String cpnNo);

    List<Cpn> cpns(String period);

    Cpn findCpnByNo(String cpnNo);

    void updateCpn(Cpn cpn);

    void createCoupon(Cpn cpn);

    void addCpnHist(CpnHist cpnHist);
}
