package choi.choice.service;

import choi.choice.domain.Pay;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PayService {

    void createPay(Pay pay, HttpServletRequest request);

    Pay findPayByMbrId(String MbrId);

    List<Pay> findPays();
}
