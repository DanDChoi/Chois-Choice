package choi.choice.service;

import choi.choice.domain.Cpn;
import java.util.List;

public interface CouponService {

    Cpn couponDetail(String cpnNo);

    List<Cpn> cpnList();

    void createCoupon(Cpn cpn);

}
