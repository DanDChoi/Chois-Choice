package choi.choice.service;

import choi.choice.domain.Cpn;
import choi.choice.domain.CpnHist;
import choi.choice.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final CouponRepository couponRepository;
    @Override
    public Cpn couponDetail(String cpnNo) {
        return couponRepository.cpnDetail(cpnNo);
    }

    @Override
    public List<Cpn> cpnList(String period){
        return couponRepository.cpns(period);}

    @Override
    public void createCoupon(Cpn cpn) {
        couponRepository.createCoupon(cpn);

        CpnHist cpnHist = null;

        cpnHist.setCpnHistTurn(1);
        cpnHist.setCpnNo(cpn.getCpnNo());
        cpnHist.setCpnBegDt(cpn.getCpnBegDt());
        cpnHist.setCpnEndDt(cpn.getCpnEndDt());
        cpnHist.setCpnDcAmt(cpn.getCpnDcAmt());
        cpnHist.setCpnDcRate(cpn.getCpnDcRate());
        cpnHist.setCpnTpCd(cpn.getCpnTpCd());
        cpnHist.setRegtrId(cpn.getRegtrId());
        cpnHist.setRegDt(cpn.getRegDt());
        cpnHist.setUdterId(cpn.getUdterId());
        cpnHist.setUdtDt(cpn.getUdtDt());

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, 9999);
        c1.set(Calendar.MONTH, 12);
        c1.set(Calendar.DATE, 31);
        c1.set(Calendar.HOUR, 23);
        c1.set(Calendar.MINUTE, 59);

        Date endDt = c1.getTime();
        //TODO HIST DT 선분데이터
        cpnHist.setHistEndDt(endDt);

        couponRepository.addCpnHist(cpnHist);
    }

    @Override
    public List<Cpn> validCpns(String period) {
        List<Cpn> cpns = couponRepository.cpns(period);
        return cpns;
    }

    @Override
    public Cpn findCpnByNo(String cpnNo) {
        Cpn cpn = couponRepository.findCpnByNo(cpnNo);
        return cpn;
    }

    @Override
    public void updateCpn(Cpn cpn) {
        couponRepository.updateCpn(cpn);

        int newHistTurn = couponRepository.cpnHistTurn(cpn.getCpnNo()) + 1;
        Date now = new Date();

        CpnHist cpnHist = null;

        cpnHist.setCpnHistTurn(newHistTurn);
        cpnHist.setCpnNo(cpn.getCpnNo());
        cpnHist.setCpnBegDt(cpn.getCpnBegDt());
        cpnHist.setCpnEndDt(cpn.getCpnEndDt());
        cpnHist.setCpnDcAmt(cpn.getCpnDcAmt());
        cpnHist.setCpnDcRate(cpn.getCpnDcRate());
        cpnHist.setCpnTpCd(cpn.getCpnTpCd());
        cpnHist.setRegtrId(cpn.getRegtrId());
        cpnHist.setRegDt(cpn.getRegDt());
        cpnHist.setUdterId(cpn.getUdterId());
        cpnHist.setUdtDt(cpn.getUdtDt());

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, 9999);
        c1.set(Calendar.MONTH, 12);
        c1.set(Calendar.DATE, 31);
        c1.set(Calendar.HOUR, 23);
        c1.set(Calendar.MINUTE, 59);

        Date endDt = c1.getTime();
        cpnHist.setHistBegDt(now);
        cpnHist.setHistEndDt(endDt);

        couponRepository.addCpnHist(cpnHist);
    }
}
