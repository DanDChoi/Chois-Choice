package choi.choice.repository;

import choi.choice.domain.Ord;
import choi.choice.domain.OrdGod;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderRepository {

    void addOrd(Ord ord);

    void addOrdGod(OrdGod ordGod);

    Ord findOne(String ordNo);

    List<Ord> findAll(Sort regDt);

    void deleteByOrdNo(String ordNo);

    Boolean existOrd(String ordNo);

    List<Ord> findDrctOrd(String status);

    List<Ord> findOrdsByMbrNo(String mbrNo);

    List<OrdGod> findBestGoods();
}
