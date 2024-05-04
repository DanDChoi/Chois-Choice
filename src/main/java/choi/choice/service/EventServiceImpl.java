package choi.choice.service;

import choi.choice.domain.Evt;
import choi.choice.domain.EvtPrize;
import choi.choice.domain.EvtReply;
import choi.choice.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final SessionManager sessionManager;

    @Override
    public void createEvt(@ModelAttribute Evt evt, HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String evtNo = "E" + format.format(date) + timeMillis;

        String regtr = sessionManager.getSession(request).getMbrId();

        evt.setEvtNo(evtNo);
        evt.setRegDt(date);
        evt.setRegtrId(regtr);
        evt.setUdtDt(date);
        evt.setUdterId(regtr);

        eventRepository.createEvt(evt);

        EvtPrize evtPrize = null;

        int evtPrizeSn = Integer.parseInt(format.format(date) + timeMillis);
        evtPrize.setEvtNo(evtNo);
        evtPrize.setEvtPartcptnSn(evtPrizeSn);
        evtPrize.setRegtrId(regtr);
        evtPrize.setRegDt(date);
        evtPrize.setUdterId(regtr);
        evtPrize.setUdtDt(date);

        eventRepository.createEvtPrize(evtPrize);

    }

    @Override
    public List<Evt> findAll() {
        List<Evt> evt = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return evt;
    }
    @Override
    public Evt findEvtByNo(String evtNo) {
        return eventRepository.findEvtByNo(evtNo);
    }

    @Override
    public void updateEvt(Evt evt) {
        eventRepository.updateEvt(evt);
    }

    @Override
    public void deleteEvt(String evtNo) {
        eventRepository.deleteEvt(evtNo);
    }
}

