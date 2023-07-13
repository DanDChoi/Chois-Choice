package choi.choice.service;

import choi.choice.domain.Pay;
import choi.choice.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService{

    private final PayRepository payRepository;

    @Override
    public void createPay(Pay pay) {

    }

    @Override
    public Pay findPayByMbrId(String MbrId) {
        return null;
    }

    @Override
    public List<Pay> findPays() {
        return null;
    }
}
