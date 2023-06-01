package choi.choice.repository;


import choi.choice.domain.Evt;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EventRepository{
    List<Evt> findAll(Sort regDt);

    void createEvt(Evt evt);
}
