package choi.choice.service;

import choi.choice.domain.Evt;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
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
    }

    @Override
    public List<Evt> findAll() {
        List<Evt> evt = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return evt;
    }
    @Override
    public Evt findEvtByNo(String evtNo) {
        return null;
    }

    @Override
    public void deleteEvt(String evtNo) {

    }
}

