package choi.choice.service;

import choi.choice.domain.Pay;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PayService {

    void createPay(Pay pay, HttpServletRequest request);

    Pay findPayByPayNo(String payNo);

    Pay findPayByOrdNo(String ordNo);

    List<Pay> findPays();

    void deletePay(String payNo);

    void addClmPay(Pay pay, String originPayNo);

    void editPay(String payNo);
}
