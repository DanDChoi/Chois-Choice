package choi.choice.service;

import choi.choice.domain.Evt;

import java.util.List;

public interface EventService {

    public List<Evt> findAll();
    public <S extends Evt> List<S> saveAll(Iterable<S> entities);
    public Evt getById(Long aLong);
    public void deleteAllById(Iterable<? extends Long> longs);
}
