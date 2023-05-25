package choi.choice.service;

import choi.choice.domain.Ord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private MemberService memberService;

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
        return null;
    }

    @Override
    public List<Ord> findAll() {
        return null;
    }

    @Override
    public void deleteOrd(String ordNo) {

    }
}
