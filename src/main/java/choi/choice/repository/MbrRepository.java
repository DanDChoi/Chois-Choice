package choi.choice.repository;

import choi.choice.domain.Mbr;
import choi.choice.domain.MbrGrd;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface MbrRepository {

    void save(Mbr mbr);

    void saveGrd(MbrGrd mbrGrd);
    Mbr findById(String id);

    Mbr findByEmail(String email);
    List<Mbr> findAll(Sort regDt);

    MbrGrd findGrdByNo(Long mbrNo);

    void deleteById(String id);

    Boolean existMbr(String id);

    List<Mbr> findMbrByGrd(String grd);
}
