package choi.choice.service;

import choi.choice.domain.Evt;
import choi.choice.domain.EvtPrize;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventService {

    void createEvt(Evt evt, List<EvtPrize> evtPrizes, HttpServletRequest request);
    List<Evt> findAll();
    Evt findEvtByNo(String evtNo);

    void updateEvt(Evt evt);
    void deleteEvt(String evtNo);
}
