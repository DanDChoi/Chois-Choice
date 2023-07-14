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
        String loginId = sessionManager.getSession(request).getMbrId();

        pay.setPayDt(date);
        pay.setRegDt(date);
        pay.setRegtrId(loginId);
        pay.setUdtDt(date);
        pay.setUdterId(loginId);
    }

    @Override
    public Pay findPayByMbrId(String MbrId) {
        return null;
    }

    @Override
    public List<Pay> findPays() {
        return null;
    }
}
