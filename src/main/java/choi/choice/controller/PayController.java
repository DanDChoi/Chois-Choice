package choi.choice.controller;

import choi.choice.domain.Pay;
import choi.choice.domain.SystemPK;
import choi.choice.repository.PayRepository;
import choi.choice.service.IdGenService;
import choi.choice.service.PayService;
import choi.choice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;
    private final PayRepository payRepository;
    private final SessionManager sm;
    IdGenService idGenService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPayPost(Pay pay, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
        payService.createPay(pay, request);
        return "ok";
    }

    @RequestMapping(value = "/eliminate", method = RequestMethod.GET)
    public void eliminatePay(String payNo) {
        payService.deletePay(payNo);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detailPay(String payNo, String ordNo, Model model){
        if (payNo != null && payNo != "") {
            Pay pay = payService.findPayByPayNo(payNo);
            model.addAttribute("pay", pay);
        }
        if (ordNo != null && ordNo != "") {
            Pay pay = payService.findPayByOrdNo(ordNo);
            model.addAttribute("pay", pay);
        }
        return "/detail/"+payNo;
    }

    @RequestMapping(value = "/updatePay", method = RequestMethod.POST)
    public String updatePay(String payNo, Pay pay, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
        String loginId = sm.getSession(request).getMbrId();

        pay.setUdterId(loginId);

        payService.editPay(payNo, pay);
        return "/detail/"+payNo;
    }
}
