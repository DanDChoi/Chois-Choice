package choi.choice.service;

import choi.choice.domain.Pay;
import choi.choice.repository.PayRepository;
import lombok.RequiredArgsConstructor;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
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
    public List<Pay> findPays() {
        return null;
    }

    @Override
    public void deletePay(String payNo) {

    }
}
