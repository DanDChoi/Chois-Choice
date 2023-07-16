package choi.choice.repository;

import choi.choice.domain.Pay;

public interface PayRepository {

    void create(Pay pay);

    Pay findByNo(String payNo);

}
