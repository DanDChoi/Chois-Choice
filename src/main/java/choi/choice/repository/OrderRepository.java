package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderRepository {

    void addOrd(Ord ord);

    void addOrdGood(OrdGood ordGood);

    void addPay(Pay pay);

    void addLgsDlv(LgsDlv lgsDlv);

    void addLgsDlvsp(LgsDlvsp lgsDlvsp);

    void addLgsDlivyDrctGood(LgsDlivyDrctGood lgsDlivyDrctGood);

    Ord findOne(String ordNo);

    List<Ord> findAll(Sort regDt);

    void deleteByOrdNo(String ordNo);

    Boolean existOrd(String ordNo);

    List<Ord> findDrctOrd(String status);

    List<Ord> findOrdsByMbrNo(Long mbrNo);

    List<OrdGood> findBestGoods();

    List<OrdGood> findOrdGoods(String ordNo);

    Pay findPayByOrdNo(String ordNo);

    List<PayRfd> findPayRfdByPayNo(String payNo);
}
