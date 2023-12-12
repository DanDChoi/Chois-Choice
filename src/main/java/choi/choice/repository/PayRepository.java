package choi.choice.repository;

import choi.choice.domain.Pay;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PayRepository {

    void create(Pay pay);

    Pay findByNo(String payNo);

    Pay findByOrdNo(String ordNo);

    List<Pay> findPays(Sort regDt);

    void deletePay(String payNo);
}
