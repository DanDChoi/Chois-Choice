package choi.choice.service;

import choi.choice.domain.Ord;
import choi.choice.repository.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private MemberService memberService;
    private OrderRepository orderRepository;

    @Override
    public void createOrd(@ModelAttribute Ord ord) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date = new Date();

        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String ordNo = "O" + format.format(date) + timeMillis;

        ord.setOrdNo(ordNo);
        ord.setOrdDt(format.format(date));
        ord.setRegtrId("");
        ord.setRegDt(date);
        ord.setUdtDt(date);

        orderRepository.add(ord);
    }

    @Override
    public Ord findOne(String ordNo) {
        Ord ord = orderRepository.findOne(ordNo);
        return ord;
    }

    @Override
    public List<Ord> findAll() {
        List<Ord> ords = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return ords;
    }

    @Override
    public void deleteOrd(String ordNo) {

    }
}
