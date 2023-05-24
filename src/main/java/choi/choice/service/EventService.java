package choi.choice.service;

import choi.choice.domain.Evt;

import java.util.List;

public interface EventService {

    void createEvt(Evt evt);
    List<Evt> findAll();
    <S extends Evt> List<S> saveAll(Iterable<S> entities);
    Evt getById(Long aLong);
    void deleteAllById(Iterable<? extends Long> longs);
}
