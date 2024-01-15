package choi.choice.service;

import choi.choice.domain.Cpn;
import java.util.List;

public interface CouponService {

    Cpn couponDetail(String cpnNo);

    List<Cpn> cpnList(String period);

    void createCoupon(Cpn cpn);

    List<Cpn> validCpns(String period);

    Cpn findCpnByNo(String cpnNo);

    void updateCpn(Cpn cpn);

}
