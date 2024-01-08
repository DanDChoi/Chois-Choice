package choi.choice.service;

import choi.choice.domain.Cpn;
import choi.choice.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
