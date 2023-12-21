package choi.choice.service;

import choi.choice.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    void createOrd(Ord ord, OrdGood ordGood, Good good, HttpServletRequest request);

    Ord findOne(String ordNo);
    List<Ord> findAll();
    void deleteOrd(String ordNo);

    List<Cpn> findAllCpn(String mbrNo);
    List<Ord> findOrdsByMbrNo(String mbrNo);

    List<OrdGood> findBestGoods();

    void createClm(Ord ord, Clm clm, OrdGood ordGood, HttpServletRequest request);

    List<OrdGood> findOrdGoods(String ordNo);
}
