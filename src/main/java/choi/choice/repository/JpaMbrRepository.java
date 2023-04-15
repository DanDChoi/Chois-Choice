package choi.choice.repository;

import choi.choice.domain.Mbr;
import choi.choice.domain.MbrGrd;
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
    public void saveGrd(MbrGrd mbrGrd) {
        em.persist(mbrGrd);
    }

    @Override
    public Mbr findById(String id) {
        Mbr mbr = em.find(Mbr.class, id);
        em.persist(mbr);
        return mbr;
    }

    @Override
    public Mbr findByEmail(String email) {
        Mbr mbr = em.find(Mbr.class, email);
        return mbr;
    }
}
