package choi.choice.repository;


import choi.choice.domain.Evt;
import choi.choice.domain.EvtPrize;
import choi.choice.domain.EvtReply;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EventRepository{
    List<Evt> findAll(Sort regDt);

    void createEvt(Evt evt);

    void createEvtPrize(EvtPrize evtPrize);

    void createEvtReply(EvtReply evtReply);

    Evt findEvtByNo(String evtNo);

    void updateEvt(Evt evt);
    void deleteEvt(String evtNo);

    Boolean existEvt(String evtNo);
}
