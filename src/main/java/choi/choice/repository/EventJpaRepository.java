package choi.choice.repository;

import choi.choice.domain.Evt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EventJpaRepository implements EventRepository{

    private final EntityManager em;

    @Autowired
    public EventJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Evt> findAll() {
        return null;
    }

    @Override
    public void createEvt(Evt evt) {

    }
}
