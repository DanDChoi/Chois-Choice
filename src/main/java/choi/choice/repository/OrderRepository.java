package choi.choice.repository;

import choi.choice.domain.Ord;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderRepository {

    void add(Ord ord);

    Ord findOne(String ordNo);

    List<Ord> findAll(Sort regDt);
}
