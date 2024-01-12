package choi.choice.controller;

import choi.choice.domain.*;
import choi.choice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final CouponService couponService;

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

    @GetMapping("/mbrDetail")
    public String adminMbrDetail(HttpServletRequest request, Model model, String mbrId) {
        Mbr mbr = memberService.findById(mbrId);

        model.addAttribute("mbr", mbr);

        return "mbrDetail/" + mbrId;
    }

    @PostMapping("/mbrUpdate")
    public void adminMbrUpdate(HttpServletRequest request, String mbrId) {
        Mbr mbr = memberService.findById(mbrId);
        memberService.updateMbr(mbr);
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

    @PostMapping("/goodUpdate")
    public void adminGoodUpdate(@RequestBody Good good, HttpServletRequest request) {
        goodService.updateGood(good);
    }

    @GetMapping("/evtList")
    public String adminEvtList(HttpServletRequest request, Model model) {
        List<Evt> evts = eventService.findAll();

        model.addAttribute("evts", evts);

        return "evtList";
    }

    @GetMapping("/evtDetail")
    public String adminEvtDetail(HttpServletRequest request, Model model, String evtNo) {
        Evt evt = eventService.findEvtByNo(evtNo);

        model.addAttribute("evt", evt);

        return "evtDetail/" + evtNo;
    }

    @PostMapping("/evtUpdate")
    public void adminEvtUpdate(@RequestBody Evt evt, HttpServletRequest request) {
        eventService.updateEvt(evt);
    }

    @GetMapping("/payList")
    public String adminPayList(HttpServletRequest request, Model model) {
        List<Pay> pays = payService.findPays();

        model.addAttribute("pays", pays);

        return "payList";
    }

    @GetMapping("/payDetail")
    public String adminPayDetail(HttpServletRequest request, Model model, String payNo) {
        Pay pay = payService.findPayByPayNo(payNo);

        model.addAttribute("pay", pay);

        return "payDetail/" + payNo;
    }

    @GetMapping("/cpnList")
    public String adminCpnList(HttpServletRequest request, Model model) {
        List<Cpn> cpns = couponService.validCpns();

        model.addAttribute("cpns", cpns);

        return "cpnList";
    }

    @GetMapping("/cpnDetail")
    public String adminCpnDetail(HttpServletRequest request, Model model, String cpnNo) {
        Cpn cpn = couponService.findCpnByNo(cpnNo);

        model.addAttribute("cpn", cpn);

        return "cpnDetail/" + cpnNo;
    }

    @PostMapping("/cpnUpdate")
    public void adminCpnUpdate(HttpServletRequest request, Cpn cpn) {
        String loginId = sessionManager.getSession(request).getMbrId();

        cpn.setUdterId(loginId);

        couponService.updateCpn(cpn);
    }
    @GetMapping("/reviews")
    public String adminReviews(HttpServletRequest request, Model model) {
        List<GoodReview> reviews = goodService.findAllReviews();

        model.addAttribute("reviews", reviews);

        return "reviews";
    }

    @GetMapping("/reviewDetail")
    public String adminReviewDetail(HttpServletRequest request, Model model, String goodNo) {
        List<GoodReview> reviews = goodService.findRvByNo(goodNo);

        model.addAttribute("review", reviews);

        return "reviewDetail/" + goodNo;
    }

    @PostMapping("/reviewUpdate")
    public void adminReviewUpdate(HttpServletRequest request, GoodReview review) {
        goodService.updateReview(review);
    }

    @GetMapping("/reviewDelete")
    public String admindReviewDelete(HttpServletRequest request, Model model, String goodNo) {
        goodService.deleteReview(goodNo);
        return "reviews";
    }
}
