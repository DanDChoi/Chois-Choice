package choi.choice.service;

import choi.choice.domain.Ord;

import java.util.List;

public interface OrderService {

    void createOrd(Ord ord);

    Ord findOne(String ordNo);
    List<Ord> findAll();

    void deleteOrd(String ordNo);
}
