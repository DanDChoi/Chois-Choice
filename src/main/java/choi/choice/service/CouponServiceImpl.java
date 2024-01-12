package choi.choice.service;

import choi.choice.domain.Cpn;
import choi.choice.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public List<Cpn> cpnList(){
        return couponRepository.cpns();}

    @Override
    public void createCoupon(Cpn cpn) {
        couponRepository.createCoupon(cpn);
    }

    @Override
    public List<Cpn> validCpns() {
        List<Cpn> cpns = couponRepository.cpns();
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
    }
}
