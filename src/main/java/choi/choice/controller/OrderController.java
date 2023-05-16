package choi.choice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ord")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping("")
    public String ordForm(){
        return "store";
    }

    @GetMapping("/add")
    public void createOrd(){
        ordRepository.add();
    }
}
