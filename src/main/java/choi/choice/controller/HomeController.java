package choi.choice.controller;

import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MbrRepository mbrRepository;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public String home(@SessionAttribute(value = "loginMember", required = false)Mbr loginMember, Model model, HttpServletRequest request){

//        Mbr loginMbr = (Mbr)sessionManager.getSession(request);

        if (loginMember == null) {
            return "theme/index";
        }

        model.addAttribute("member", loginMember);
        return "theme/index";
    }
}
