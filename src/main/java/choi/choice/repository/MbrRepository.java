package choi.choice.repository;

import choi.choice.domain.Mbr;


public interface MbrRepository {

    void save(Mbr mbr);
    Mbr findById(String id);

    Mbr findByEmail(String email);
}
