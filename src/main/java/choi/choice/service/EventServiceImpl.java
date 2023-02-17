package choi.choice.service;

import choi.choice.domain.evt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    @Override
    public List<evt> findAll() {
        return null;
    }

    @Override
    public <S extends evt> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public evt getById(Long aLong) {
        return null;
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }
}
