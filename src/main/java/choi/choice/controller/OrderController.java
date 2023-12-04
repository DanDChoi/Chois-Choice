package choi.choice.controller;

import choi.choice.domain.Good;
import choi.choice.domain.Mbr;
import choi.choice.domain.Ord;
import choi.choice.domain.OrdGood;
import choi.choice.repository.OrderRepository;
import choi.choice.service.MemberService;
import choi.choice.service.OrderService;
import choi.choice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/ord")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    private final SessionManager sessionManager;

    private final MemberService memberService;


    @GetMapping("")
    public String ordForm(HttpServletRequest request){
        String mbrId = sessionManager.getSession(request).getMbrId();
        Mbr mbr = memberService.findById(mbrId);
        orderService.findAllCpn(mbr.getMbrId());
        return "store";
    }

    @PostMapping("/add")
    public void createOrd(@ModelAttribute Ord ord, OrdGood ordGood, Good good, HttpServletRequest request){
        orderService.createOrd(ord, ordGood, good, request);
    }

    @GetMapping("/find")
    public Ord findOrdByOrdNo (String ordNo){
        return orderService.findOne(ordNo);
    }
}
