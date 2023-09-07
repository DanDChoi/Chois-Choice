package choi.choice.controller;

import choi.choice.domain.Good;
import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.GoodService;
import choi.choice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MbrRepository mbrRepository;
    private final SessionManager sessionManager;
    private final GoodService goodService;

    @GetMapping("/")
    public String home(@SessionAttribute(value = "loginMember", required = false)Mbr loginMember, Model model, HttpServletRequest request){

        log.info("homecontroller 진입");
        if (loginMember == null) {
            log.info("loginMember If 진입 ={} ", loginMember);
            return "index";
        }
        log.info("loginMember if 미진입 ={}", loginMember);
        model.addAttribute("member", loginMember);
        return "index";
    }

    @GetMapping("/men")
    public String menCate(Model model, HttpServletRequest request) {
        List<Good> menGoods = goodService.findGoodsByCate("men");
        model.addAttribute("menGoods", menGoods);
        return "menList";
    }
    @GetMapping("/women")
    public String womenCate(Model model, HttpServletRequest request){
        List<Good> womenGoods = goodService.findGoodsByCate("women");
        model.addAttribute("menGoods", womenGoods);
        return "womenList";
    }
    @GetMapping("/life")
    public String lifeCate(Model model, HttpServletRequest request){
        List<Good> lifeGoods = goodService.findGoodsByCate("life");
        model.addAttribute("menGoods", lifeGoods);
        return "lifeList";
    }
}
