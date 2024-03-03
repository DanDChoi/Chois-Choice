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

    int cpnHistTurn(String cpnNO);

    void executeCpn(String cpnNo);

    CpnHist findMaxCpnTurnHistCpn(String cpnNo);

    void updateCpnHist(CpnHist cpnHist);

    List<Cpn> mbrCpnList(Long mbrNo);
}
