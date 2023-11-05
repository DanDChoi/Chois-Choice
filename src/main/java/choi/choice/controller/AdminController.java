package choi.choice.controller;

import choi.choice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdminController {

    SessionManager sessionManager;

    @GetMapping("/page")
    public String adminPage(HttpServletRequest request){
        String mbrId = sessionManager.getSession(request).getMbrId();
        if (mbrId.equals("admin")){
            return "adminPage";
        } else {
            return "redirect:/";
        }
    }
}
