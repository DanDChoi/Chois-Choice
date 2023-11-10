package choi.choice.controller;

import choi.choice.domain.Evt;
import choi.choice.domain.Good;
import choi.choice.domain.Mbr;
import choi.choice.domain.Pay;
import choi.choice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdminController {

    private final SessionManager sessionManager;
    private final MemberService memberService;
    private final GoodService goodService;
    private final EventService eventService;
    private final PayService payService;

    @GetMapping("/page")
    public String adminPage(HttpServletRequest request, Model model) {
        String mbrId = sessionManager.getSession(request).getMbrId();
        if (mbrId.equals("admin")) {
            return "adminPage";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/mbrList")
    public String adminMbrList(HttpServletRequest request, Model model) {
        List<Mbr> mbrs = memberService.findAll();

        model.addAttribute("mbrs", mbrs);

        return "mbrList";
    }

    @GetMapping("/goodsList")
    public String adminGoodsList(HttpServletRequest request, Model model) {
        List<Good> goods = goodService.findAll();

        model.addAttribute("goods", goods);

        return "goodsList";
    }

    @GetMapping("/evtList")
    public String adminEvtList(HttpServletRequest request, Model model) {
        List<Evt> evts = eventService.findAll();

        model.addAttribute("evts", evts);

        return "evtList";
    }

    @GetMapping("/payList")
    public String adminPayList(HttpServletRequest request, Model model) {
        List<Pay> pays = payService.findPays();

        model.addAttribute("pays", pays);

        return "payList";
    }
}
