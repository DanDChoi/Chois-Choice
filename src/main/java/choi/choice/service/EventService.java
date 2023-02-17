package choi.choice.service;

import choi.choice.domain.evt;

import java.util.List;

public interface EventService {

    public List<evt> findAll();
    public <S extends evt> List<S> saveAll(Iterable<S> entities);
    public evt getById(Long aLong);
    public void deleteAllById(Iterable<? extends Long> longs);
}
