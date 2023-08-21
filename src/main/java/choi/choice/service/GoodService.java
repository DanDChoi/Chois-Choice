package choi.choice.service;

import choi.choice.domain.Cpn;
import choi.choice.domain.Good;
import choi.choice.domain.GoodReview;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodService {

    void add(Good good, HttpServletRequest request);
    Good findByNo(String id);

    List<Good> findAll();
    void deleteByNo(String id);

    GoodReview findRvByNo(String goodNo);

    List<Cpn> validCpns();
}
