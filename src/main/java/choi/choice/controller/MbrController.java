package choi.choice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class MbrController {

    @GetMapping("/join")
    public String joinForm(){
        return "/mbr/join";
    }

    @PostMapping("/join")
    public void mbrJoin(@ModelAttribute HttpServletRequest request) {
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/mbr/login";
    }
}
