package choi.choice.service;

import choi.choice.domain.Evt;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventService {

    void createEvt(Evt evt, HttpServletRequest request);
    List<Evt> findAll();
    Evt findEvtByNo(String evtNo);

    void deleteEvt(String evtNo);
}
