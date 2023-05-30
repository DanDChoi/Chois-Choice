package choi.choice.service;

import choi.choice.domain.Evt;
import choi.choice.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventRepository eventRepository;
    @Override
    public List<Evt> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public <S extends Evt> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Evt getById(Long aLong) {
        return null;
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }
}
