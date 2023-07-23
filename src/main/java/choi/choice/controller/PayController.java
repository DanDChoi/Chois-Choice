package choi.choice.controller;

import choi.choice.repository.PayRepository;
import choi.choice.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;
    private final PayRepository payRepository;

    @GetMapping("add")
    public String addPay() {
        return 'pay/addForm';
    }

    @PostMapping("add")
    public String addPayPost(){
        return 'ok';
    }
}
