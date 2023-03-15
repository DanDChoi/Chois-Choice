package choi.choice.repository;

import choi.choice.domain.Mbr;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MbrRepository {

    void save(Mbr mbr);
    Mbr findById(String id);

    Mbr findByEmail(String email);
}
