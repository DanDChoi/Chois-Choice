package choi.choice.service;

import choi.choice.domain.Good;

import java.util.List;

public interface GoodService {

    void add(Good good);
    Good findByNo(String id);
    void deleteByNo(String id);
}
