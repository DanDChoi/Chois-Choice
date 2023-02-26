package choi.choice.service;

import choi.choice.domain.Evt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    @Override
    public List<Evt> findAll() {
        return null;
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
