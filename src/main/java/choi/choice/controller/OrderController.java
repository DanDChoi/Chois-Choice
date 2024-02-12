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


    @GetMapping("")
    public String ordForm(Good good, Model model, HttpServletRequest request) {
        String mbrId = sessionManager.getSession(request).getMbrId();
        Mbr mbr = memberService.findById(mbrId);
        Good ordGood = goodService.findByNo(good.getGoodNo());
        List<Cpn> mbrCpn = orderService.findAllCpn(mbr.getMbrId());

        model.addAttribute("ordGood", ordGood);
        model.addAttribute("mbr", mbr);
        model.addAttribute("mbrCpn", mbrCpn);
        return "store";
    }

    @PostMapping("/add")
    public void createOrd(@ModelAttribute Ord ord, OrdGood ordGood, Good good, HttpServletRequest request) {
        orderService.createOrd(ord, ordGood, good, request);
    }

    @GetMapping("/find")
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

    @GetMapping("/clmForm")
    public String createClm(Ord ord, OrdGood ordGood, Model model) {
        Ord findOrd = orderService.findOne(ord.getOrdNo());
        List<OrdGood> findOrdGoods = orderService.findOrdGoods(ord.getOrdNo());

        model.addAttribute("ord", findOrd);
        model.addAttribute("ordGoods", findOrdGoods);
        return "clmForm";
    }

    @PostMapping("/addClm")
    public void createClm(Ord ord, Clm clm, OrdGood ordGood, HttpServletRequest request) {
        claimService.createClm(ord, clm, ordGood);
    }

    @GetMapping("/clmDetail")
    public String clmDetail(Ord ord, ClmGood clmGood, Clm clm, HttpServletRequest request, Model model) {
        Clm clmDetail = claimService.findClm(clm.getClmNo());
        model.addAttribute("clm", clmDetail);
        return "clmDetail/" + clm.getClmNo();
    }

    @PostMapping("/editClm")
    public void editClm(Clm clm, ClmGood clmGood, Model model) {
        claimService.editClm(clm, clmGood);
    }
}
