package choi.choice.service;

import choi.choice.domain.*;
import choi.choice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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
    private final PayService payService;

    @Override
    public void createOrd(@ModelAttribute Ord ord, OrdGood ordGood, Good good, HttpServletRequest request) {

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

        List<OrdGood> ordGoods = new ArrayList<>();

        for (int i = 0; i < ordGoods.size(); i++) {
            ordGood.setOrdNo(ordNo);
            ordGood.setOrdGoodTurn(i+1);
            ordGood.setGoodNo(good.getGoodNo());
            ordGood.setGoodNm(good.getGoodNm());
            ordGood.setStdCtgryNo(good.getStdCtgryNo());
            ordGood.setGoodHistNo(ordGood.getGoodHistNo());
            ordGood.setRegtrId(regtr);
            ordGood.setRegDt(date);
            ordGood.setUdterId(regtr);
            ordGood.setUdtDt(date);

            orderRepository.addOrdGod(ordGood);
        }

        orderRepository.addOrd(ord);

        Pay pay = null;

        String payNo = "P" + format.format(date) + timeMillis;
        pay.setPayNo(payNo);
        pay.setPayAmt(good.getGoodPrc());
        //일단은 무료배송만
        pay.setDlvCstYn("N");
        pay.setOrdNo(ordNo);
        pay.setPayDt(date);
        pay.setRegtrId(regtr);
        pay.setRegDt(date);
        pay.setUdterId(regtr);
        pay.setUdtDt(date);

        orderRepository.addPay(pay);

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
    public List<Cpn> findAllCpn(Long mbrNo) {
        return null;
    }

    @Override
    public List<Ord> findOrdsByMbrNo(Long mbrNo) {
        return orderRepository.findOrdsByMbrNo(mbrNo);
    }

    @Override
    public List<OrdGood> findBestGoods() {
        return orderRepository.findBestGoods();
    }


    @Override
    public List<OrdGood> findOrdGoods(String ordNo) {
        List<OrdGood> ordGoods = orderRepository.findOrdGoods(ordNo);
        return ordGoods;
    }
}
