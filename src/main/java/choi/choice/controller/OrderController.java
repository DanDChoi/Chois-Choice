package choi.choice.controller;

import choi.choice.domain.*;
import choi.choice.repository.OrderRepository;
import choi.choice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ord")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    private final SessionManager sessionManager;

    private final MemberService memberService;

    private final ClaimService claimService;

    private final GoodService goodService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String ordForm(Good good, Model model, HttpServletRequest request) {
        String mbrId = sessionManager.getSession(request).getMbrId();
        Mbr mbr = memberService.findById(mbrId);
        Good ordGood = goodService.findByNo(good.getGoodNo());
        List<Cpn> mbrCpn = orderService.findAllCpn(mbr.getMbrNo());

        model.addAttribute("ordGood", ordGood);
        model.addAttribute("mbr", mbr);
        model.addAttribute("mbrCpn", mbrCpn);
        return "store";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void createOrd(@ModelAttribute Ord ord, OrdGood ordGood, Good good, LgsDlvsp lgsDlvsp, HttpServletRequest request) {
        orderService.createOrd(ord, ordGood, good, lgsDlvsp, request);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Ord findOrdByOrdNo(String ordNo) {
        return orderService.findOne(ordNo);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public String ordDetail(@ModelAttribute String ordNo, Model model, HttpServletRequest request) {
        Ord ord = orderService.findOne(ordNo);
        List<OrdGood> ordGoods = orderService.findOrdGoods(ordNo);

        model.addAttribute("ord", ord);
        model.addAttribute("ordGoods", ordGoods);

        return "/detail/" + ordNo;
    }

    @RequestMapping(value = "/refund.json", method = RequestMethod.POST)
    public void refundApply(String ordNo, HttpServletRequest request) {

        Pay pay = orderService.findPayByOrdNo(ordNo);
        List<PayRfd> existRfd = orderService.findPayRfdByPayNo(pay.getPayNo());
        int maxRfdTurn = 0;
        Date date = new Date();

        PayRfd payRfd = null;
        payRfd.setPayNo(pay.getPayNo());
        if (existRfd.size() > 0) {
            for (int i = 0; i < existRfd.size(); i++) {
                if (existRfd.get(i).getRfdTurn() > maxRfdTurn) {
                    maxRfdTurn = existRfd.get(i).getRfdTurn();
                }
            }
            payRfd.setRfdTurn(maxRfdTurn + 1);
        } else {
            payRfd.setRfdTurn(1);
        }
        payRfd.setRfdRequstDt(date);
    }

}
