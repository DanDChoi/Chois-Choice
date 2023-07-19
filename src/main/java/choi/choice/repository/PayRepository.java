package choi.choice.repository;

import choi.choice.domain.Pay;

import java.util.List;

public interface PayRepository {

    void create(Pay pay);

    Pay findByNo(String payNo);

    List<Pay> findPays(String ordNo);

    void deletePay(String payNo);
}
