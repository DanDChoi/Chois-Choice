package choi.choice.repository;

import choi.choice.domain.Evt;
import choi.choice.domain.EvtReply;
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
    public Evt findEvtByNo(String evtNo) {
        Evt evt = em.find(Evt.class, evtNo);
        em.persist(evt);
        return evt;
    }

    @Override
    public List<Evt> findAll(Sort regDt) {
        return null;
    }

    @Override
    public void createEvt(Evt evt) {
        em.persist(evt);
    }

    @Override
    public void createEvtReply(EvtReply evtReply) {
        em.persist(evtReply);
    }

    @Override
    public void deleteEvt(String evtNo) {
        Evt evt = em.find(Evt.class, evtNo);
        em.remove(evt);
    }
}
