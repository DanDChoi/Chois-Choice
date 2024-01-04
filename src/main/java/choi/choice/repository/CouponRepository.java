package choi.choice.repository;

import choi.choice.domain.Cpn;

public interface CouponRepository {

    Cpn cpnDetail(String cpnNo);
}
