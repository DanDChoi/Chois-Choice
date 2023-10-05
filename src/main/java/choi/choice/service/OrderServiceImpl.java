package choi.choice.service;

import choi.choice.domain.*;
import choi.choice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberService memberService;
    private final OrderRepository orderRepository;
    private final SessionManager sessionManager;

    @Override
    public void createOrd(@ModelAttribute Ord ord, OrdGod ordGod, Good good, HttpServletRequest request) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String ordNo = "O" + format.format(date) + timeMillis;

        String regtr = sessionManager.getSession(request).getMbrId();

        ord.setOrdNo(ordNo);
        ord.setOrdDt(format.format(date));
        ord.setRegtrId(regtr);
        ord.setRegDt(date);
        ord.setUdterId(regtr);
        ord.setUdtDt(date);

        List<OrdGod> ordGods = new ArrayList<>();

        for (int i = 0; i < ordGods.size(); i++) {
            ordGod.setOrdNo(ordNo);
            ordGod.setOrdGoodTurn(i+1);
            ordGod.setGoodNo(good.getGoodNo());
            ordGod.setGoodNm(good.getGoodNm());
            ordGod.setGoodHistNo(ordGod.getGoodHistNo());
            ordGod.setRegtrId(regtr);
            ordGod.setRegDt(date);
            ordGod.setUdterId(regtr);
            ordGod.setUdtDt(date);

            orderRepository.addOrdGod(ordGod);
        }
        Pay pay = null;
        //TODO pay set

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
        orderRepository.deleteByOrdNo(ordNo);
    }

    @Override
    public List<Cpn> findAllCpn(String mbrNo) {
        return null;
    }

    @Override
    public List<Ord> findOrdsByMbrNo(String mbrNo) {
        return orderRepository.findOrdsByMbrNo(mbrNo);
    }

    @Override
    public List<OrdGod> findBestGoods() {
        return orderRepository.findBestGoods();
    }
}
