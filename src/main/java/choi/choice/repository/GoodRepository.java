package choi.choice.repository;

import choi.choice.domain.Good;

public interface GoodRepository{

    void save(Good good);

    Good findByNo(String goodNo);

    void deleteByNo(String goodNo);
}
