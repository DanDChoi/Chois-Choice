package choi.choice.repository;

import choi.choice.domain.Evt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public Evt findById(String evtId) {
        return null;
    }

    @Override
    public List<Evt> findAll(Sort regDt) {
        return null;
    }

    @Override
    public void createEvt(Evt evt) {

    }
}
