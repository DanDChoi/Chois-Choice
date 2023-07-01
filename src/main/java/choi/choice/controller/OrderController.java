package choi.choice.controller;

import choi.choice.domain.Good;
import choi.choice.domain.Ord;
import choi.choice.domain.OrdGod;
import choi.choice.repository.OrderRepository;
import choi.choice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/ord")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    @GetMapping("")
    public String ordForm(){
        return "store";
    }

    @GetMapping("/add")
    public void createOrd(@ModelAttribute Ord ord, OrdGod ordGod, Good good, HttpServletRequest request){
        orderService.createOrd(ord, ordGod, good, request);
    }
}
