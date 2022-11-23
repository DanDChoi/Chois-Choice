package choi.choice.controller;

import choi.choice.domain.mbr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/mbr")
public class MbrController {

    @GetMapping("register")
    public String joinForm(){
        return "/theme/register";
    }

    @PostMapping("register")
    public void mbrJoin(@ModelAttribute HttpServletRequest request) {
    }

    @GetMapping("login")
    public String loginForm(){
        return "theme/login";
    }

    @PostMapping("login")
    public void login(@ModelAttribute mbr mbr, HttpServletRequest request) {
        log.info("id={}", mbr.getMbr_id());
    }
}
