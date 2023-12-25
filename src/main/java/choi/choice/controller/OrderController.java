package choi.choice.controller;

import choi.choice.domain.*;
import choi.choice.repository.OrderRepository;
import choi.choice.service.ClaimService;
import choi.choice.service.MemberService;
import choi.choice.service.OrderService;
import choi.choice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @GetMapping("")
    public String ordForm(HttpServletRequest request) {
        String mbrId = sessionManager.getSession(request).getMbrId();
        Mbr mbr = memberService.findById(mbrId);
        orderService.findAllCpn(mbr.getMbrId());
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
        orderService.createClm(ord, clm, ordGood, request);
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
