package choi.choice.repository;

import choi.choice.domain.Good;

import java.util.List;

public interface GoodRepository{

    void save(Good good);

    Good findByNo(String goodNo);

    List<Good> findAll();

    void deleteByNo(String goodNo);
}
