package choi.choice.repository;

import choi.choice.domain.Good;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GoodRepository{

    void save(Good good);

    Good findByNo(String goodNo);

    List<Good> findAll(Sort regDt);

    void deleteByNo(String goodNo);
}
