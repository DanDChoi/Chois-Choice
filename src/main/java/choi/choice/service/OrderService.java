package choi.choice.service;

import choi.choice.domain.Cpn;
import choi.choice.domain.Good;
import choi.choice.domain.Ord;
import choi.choice.domain.OrdGod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    void createOrd(Ord ord, OrdGod ordGod, Good good, HttpServletRequest request);

    Ord findOne(String ordNo);
    List<Ord> findAll();
    void deleteOrd(String ordNo);

    List<Cpn> findAllCpn(String mbrNo);
    List<Ord> findOrdsByMbrNo(String mbrNo);
}
