package choi.choice.service;

import choi.choice.domain.Pay;
import choi.choice.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService{

    private final PayRepository payRepository;

    private final SessionManager sessionManager;

    @Override
    public void createPay(Pay pay, HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String payNo = "P" + format.format(date) + timeMillis;
        String loginId = sessionManager.getSession(request).getMbrId();


        pay.setPayNo(payNo);
        pay.setPayDt(date);
        pay.setRegDt(date);
        pay.setRegtrId(loginId);
        pay.setUdtDt(date);
        pay.setUdterId(loginId);

        payRepository.create(pay);
    }

    @Override
    public Pay findPayByPayNo(String payNo) {
        return payRepository.findByNo(payNo);
    }

    @Override
    public Pay findPayByOrdNo(String ordNo) {
        return payRepository.findByOrdNo(ordNo);
    }

    @Override
    public List<Pay> findPays() {
        List<Pay> pays = payRepository.findPays(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return pays;
    }

    @Override
    public void deletePay(String payNo) {
        payRepository.deletePay(payNo);
    }

    @Override
    public void addClmPay(Pay pay, String originPayNo) {
        payRepository.addClmPay(pay, originPayNo);
    }

    @Override
    public void editPay(String payNo) {
        payRepository.editPay(payNo);
    }
}
