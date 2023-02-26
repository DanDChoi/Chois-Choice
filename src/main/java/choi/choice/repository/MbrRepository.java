package choi.choice.repository;

import choi.choice.domain.Mbr;

import java.util.Optional;

public interface MbrRepository {

    void save(Mbr mbr);
    Optional<Mbr> findById(String id);
}
