package choi.choice.service;

import choi.choice.domain.Ord;
import choi.choice.repository.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private MemberService memberService;
    private OrderRepository orderRepository;

    @Override
    public void createOrd(Ord ord) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date = new Date();


        ord.setOrdDt(format.format(date));
        ord.setRegtrId("");
        ord.setRegDt(date);
        ord.setUdtDt(date);

    }

    @Override
    public Ord findOne(String ordNo) {
        Ord ord = orderRepository.findOne(ordNo);
        return ord;
    }

    @Override
    public List<Ord> findAll() {
        List<Ord> ords = new ArrayList<>();

        return null;
    }

    @Override
    public void deleteOrd(String ordNo) {

    }
}
