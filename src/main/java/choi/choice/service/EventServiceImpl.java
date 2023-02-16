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
}
