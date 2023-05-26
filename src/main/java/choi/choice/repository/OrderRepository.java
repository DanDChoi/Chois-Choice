package choi.choice.repository;

import choi.choice.domain.Ord;

public interface OrderRepository {

    void add(Ord ord);

    Ord findOne(String ordNo);
}
