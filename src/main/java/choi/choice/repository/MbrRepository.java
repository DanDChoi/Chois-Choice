package choi.choice.repository;

import choi.choice.domain.Mbr;
import choi.choice.domain.MbrGrd;


public interface MbrRepository {

    void save(Mbr mbr);

    void saveGrd(MbrGrd mbrGrd);
    Mbr findById(String id);

    Mbr findByEmail(String email);
}
