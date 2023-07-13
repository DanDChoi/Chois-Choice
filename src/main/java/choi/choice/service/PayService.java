package choi.choice.service;

import choi.choice.domain.Pay;

import java.util.List;

public interface PayService {

    void createPay(Pay pay);

    Pay findPayByMbrId(String MbrId);

    List<Pay> findPays();
}
