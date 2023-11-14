package choi.choice.controller;

import choi.choice.domain.*;
import choi.choice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        List<MbrGrd> mbrGrds = new ArrayList<>();

        for (int i = 0; i < mbrs.size(); i++) {
            mbrGrds.add(memberService.findGrdByNo(mbrs.get(i).getMbrNo()));
        }

        model.addAttribute("mbrs", mbrs);
        model.addAttribute("mbrGrds", mbrGrds);
        return "mbrList";
    }

    @GetMapping("/goodsList")
    public String adminGoodsList(HttpServletRequest request, Model model) {
        List<Good> goods = goodService.findAll();

        model.addAttribute("goods", goods);

        return "goodsList";
    }

    @GetMapping("/goodDetail")
    public String adminGoodDetail(HttpServletRequest request, Model model, String goodNo) {
        Good good = goodService.findByNo(goodNo);
        model.addAttribute("good", good);
        return "goodDetail/" + goodNo;
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

    @GetMapping("/cpnList")
    public String adminCpnList(HttpServletRequest request, Model model) {
        List<Cpn> cpns = goodService.validCpns();

        model.addAttribute("cpns", cpns);

        return "cpnList";
    }
    @GetMapping("/reviews")
    public String adminReviews(HttpServletRequest request, Model model) {
        List<GoodReview> reviews = goodService.findAllReviews();

        model.addAttribute("reviews", reviews);

        return "reviews";
    }
}
