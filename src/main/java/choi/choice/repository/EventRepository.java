package choi.choice.repository;


import choi.choice.domain.Evt;

import java.util.List;

public interface EventRepository{
    List<Evt> findAll();

    void createEvt(Evt evt);
}
