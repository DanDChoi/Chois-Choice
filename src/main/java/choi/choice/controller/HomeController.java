package choi.choice.controller;

import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MbrRepository mbrRepository;

    @GetMapping("/")
    public String home(@CookieValue(name = "mbrId", required = false) String mbrId, Model model){
        if (mbrId == null) {
            return "theme/index";

        }
        Optional<Mbr> loginMbr = mbrRepository.findById(mbrId);
        if (loginMbr == null) {
            return "theme/index";
        }

        model.addAttribute("mbr", loginMbr);
        return "theme/index";
    }
}
