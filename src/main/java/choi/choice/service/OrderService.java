package choi.choice.service;

import choi.choice.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    void createOrd(Ord ord, OrdGood ordGood, Good good, HttpServletRequest request);

    Ord findOne(String ordNo);
    List<Ord> findAll();
    void deleteOrd(String ordNo);

    List<Cpn> findAllCpn(Long mbrNo);
    List<Ord> findOrdsByMbrNo(Long mbrNo);

    List<OrdGood> findBestGoods();


    List<OrdGood> findOrdGoods(String ordNo);
}
