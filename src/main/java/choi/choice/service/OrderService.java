package choi.choice.service;

import choi.choice.domain.Ord;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    void createOrd(Ord ord, HttpServletRequest request);

    Ord findOne(String ordNo);
    List<Ord> findAll();

    void deleteOrd(String ordNo);
}
