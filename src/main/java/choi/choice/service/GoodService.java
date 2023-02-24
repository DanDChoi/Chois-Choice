package choi.choice.service;

import choi.choice.domain.good;

import java.util.List;

public interface GoodService {

    List<good> findAll();
    void add(good good);
    good findByNo(String id);
    void deleteByNo(String id);
}
