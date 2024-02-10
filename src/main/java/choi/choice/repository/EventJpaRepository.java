package choi.choice.repository;

import choi.choice.domain.Evt;
import choi.choice.domain.EvtReply;
import choi.choice.domain.GoodReview;
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
    public void updateEvt(Evt evt) {
        String query = "update Evt e " +
                "set e.evtNm = :evtNm " +
                ",e.evtDscr = :evtDscr " +
                ",e.evtBegDt = :evtBegDt " +
                ",e.evtEndDt = :evtEndDt " +
                ",e.replyUseYn = :replyUseYn " +
                ",e.evtStatCd = :evtStatCd " +
                "where e.evtNo = :evtNo";

        Evt updateEvt = em.createQuery(query, Evt.class)
                .setParameter("evtNm", evt.getEvtNm())
                .setParameter("evtDscr", evt.getEvtDscr())
                .setParameter("evtBegDt", evt.getEvtBegDt())
                .setParameter("evtEndDt", evt.getEvtEndDt())
                .setParameter("replyUseYn", evt.getReplyUseYn())
                .setParameter("evtStatCd", evt.getEvtStatCd())
                .setParameter("evtNo", evt.getEvtNo())
                .getSingleResult();
    }

    @Override
    public void deleteEvt(String evtNo) {
        Evt evt = em.find(Evt.class, evtNo);
        em.remove(evt);
    }

    @Override
    public Boolean existEvt(String evtNo) {
        Evt evt = em.find(Evt.class, evtNo);
        if (evt == null) {
            return false;
        } else {
            return true;
        }
    }
}
