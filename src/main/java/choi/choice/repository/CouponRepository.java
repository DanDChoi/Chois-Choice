package choi.choice.repository;

import choi.choice.domain.Cpn;

import java.util.List;

public interface CouponRepository {

    Cpn cpnDetail(String cpnNo);

    List<Cpn> cpns(String period);

    Cpn findCpnByNo(String cpnNo);

    void updateCpn(Cpn cpn);

    void createCoupon(Cpn cpn);
}
