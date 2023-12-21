package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderRepository {

    void addOrd(Ord ord);

    void addOrdGod(OrdGood ordGood);

    void addPay(Pay pay);

    Ord findOne(String ordNo);

    List<Ord> findAll(Sort regDt);

    void deleteByOrdNo(String ordNo);

    Boolean existOrd(String ordNo);

    List<Ord> findDrctOrd(String status);

    List<Ord> findOrdsByMbrNo(String mbrNo);

    List<OrdGood> findBestGoods();

    void addClm(Clm clm, ClmGood clmGood);

    List<OrdGood> findOrdGoods(String ordNo);
}
