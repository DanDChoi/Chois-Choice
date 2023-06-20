package choi.choice.repository;

import choi.choice.domain.Bsk;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BasketRepository {

    void add(Bsk bsk);

    void delete(String bskNo);

    List<Bsk> findAll(Sort regDt);
}
