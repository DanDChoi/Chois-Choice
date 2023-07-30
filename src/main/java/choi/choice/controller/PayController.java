package choi.choice.controller;

import choi.choice.domain.Pay;
import choi.choice.repository.PayRepository;
import choi.choice.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;
    private final PayRepository payRepository;

    @PostMapping("add")
    public String addPayPost(Pay pay, HttpServletRequest request) {
        payService.createPay(pay, request);
        return "ok";
    }

    @GetMapping("eliminate")
    public void eliminatePay(String payNo) {
        payService.deletePay(payNo);
    }

    @GetMapping("detail")
    public String detailPay(String payNo, Model model){
        Pay pay = payService.findPayByPayNo(payNo);
        model.addAttribute("pay", pay);
        return "/detail";
    }
}
