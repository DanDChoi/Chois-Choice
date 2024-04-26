package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderRepository {

    void addOrd(Ord ord);

    void addOrdGod(OrdGood ordGood);

    void addPay(Pay pay);

    void addLgsDlv(LgsDlv lgsDlv);

    void addLgsDlivyDrctGood(LgsDlivyDrctGood lgsDlivyDrctGood);

    Ord findOne(String ordNo);

    List<Ord> findAll(Sort regDt);

    void deleteByOrdNo(String ordNo);

    Boolean existOrd(String ordNo);

    List<Ord> findDrctOrd(String status);

    List<Ord> findOrdsByMbrNo(Long mbrNo);

    List<OrdGood> findBestGoods();

    List<OrdGood> findOrdGoods(String ordNo);
}
