package choi.choice.controller;

import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MbrRepository mbrRepository;

    @GetMapping("/")
    public String home(@SessionAttribute(value = "loginMbr", required = false) String mbrEmail, Model model){
        if (mbrEmail == null) {
            return "theme/index";

        }
        Mbr loginMbr = mbrRepository.findById(mbrEmail);
        if (loginMbr == null) {
            return "theme/index";
        }

        model.addAttribute("mbr", loginMbr);
        model.addAttribute("mbrNm", loginMbr.getMbrNm());
        return "theme/index";
    }
}
