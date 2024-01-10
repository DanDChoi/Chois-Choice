package choi.choice.repository;

import choi.choice.domain.Cpn;

import java.util.List;

public interface CouponRepository {

    Cpn cpnDetail(String cpnNo);

    List<Cpn> cpns();

    Cpn findCpnByNo(String cpnNo);

    void updateCpn(Cpn cpn);

    void createCoupon(Cpn cpn);
}
