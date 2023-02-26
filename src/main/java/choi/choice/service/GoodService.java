package choi.choice.service;

import choi.choice.domain.Good;

import java.util.List;

public interface GoodService {

    List<Good> findAll();
    void add(Good good);
    Good findByNo(String id);
    void deleteByNo(String id);
}
