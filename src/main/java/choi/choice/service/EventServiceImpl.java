package choi.choice.service;

import choi.choice.domain.Evt;
import choi.choice.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Override
    public void createEvt(Evt evt) {
        eventRepository.createEvt(evt);
    }

    @Override
    public List<Evt> findAll() {
        List<Evt> evt = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return evt;
    }
    @Override
    public Evt getById(Long aLong) {
        return null;
    }

    }
