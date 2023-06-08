package choi.choice.service;

import choi.choice.domain.Evt;

import java.util.List;

public interface EventService {

    void createEvt(Evt evt);
    List<Evt> findAll();
    Evt findEvtByNo(String evtNo);

    void deleteEvt(String evtNo);
}
