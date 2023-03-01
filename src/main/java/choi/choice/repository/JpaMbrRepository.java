package choi.choice.repository;

import choi.choice.domain.Mbr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaMbrRepository implements MbrRepository{

    private final EntityManager em;

    @Autowired
    public JpaMbrRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Mbr mbr) {
        em.persist(mbr);
    }

    @Override
    public Optional<Mbr> findById(String id) {
        Mbr mbr = em.find(Mbr.class, id);
        return Optional.ofNullable(mbr);
    }

}
