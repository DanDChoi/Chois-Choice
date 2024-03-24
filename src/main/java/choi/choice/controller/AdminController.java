package choi.choice.controller;

import choi.choice.domain.*;
import choi.choice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final AdminService adminService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String adminPage(HttpServletRequest request, Model model) {
        String mbrId = sessionManager.getSession(request).getMbrId();
        if (mbrId.equals("admin")) {
            return "adminPage";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/mbrList", method = RequestMethod.GET)
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

    @RequestMapping(value = "/mbrDetail", method = RequestMethod.GET)
    public String adminMbrDetail(HttpServletRequest request, Model model, String mbrId) {
        Mbr mbr = memberService.findById(mbrId);

        model.addAttribute("mbr", mbr);

        return "mbrDetail/" + mbrId;
    }

    @RequestMapping(value = "/mbrUpdate", method = RequestMethod.POST)
    public void adminMbrUpdate(HttpServletRequest request, String mbrId) {
        Mbr mbr = memberService.findById(mbrId);
        memberService.updateMbr(mbr);
    }

    @RequestMapping(value = "/goodsList", method = RequestMethod.GET)
    public String adminGoodsList(HttpServletRequest request, Model model) {
        List<Good> goods = goodService.findAll();

        model.addAttribute("goods", goods);

        return "goodsList";
    }

    @RequestMapping(value = "/goodDetail", method = RequestMethod.GET)
    public String adminGoodDetail(HttpServletRequest request, Model model, String goodNo) {
        Good good = goodService.findByNo(goodNo);
        model.addAttribute("good", good);
        return "goodDetail/" + goodNo;
    }

    @RequestMapping(value = "/goodUpdate", method = RequestMethod.POST)
    public void adminGoodUpdate(@RequestBody Good good, HttpServletRequest request) {
        goodService.updateGood(good);
    }

    @RequestMapping(value = "/evtList", method = RequestMethod.GET)
    public String adminEvtList(HttpServletRequest request, Model model) {
        List<Evt> evts = eventService.findAll();

        model.addAttribute("evts", evts);

        return "evtList";
    }

    @RequestMapping(value = "/evtDetail", method = RequestMethod.GET)
    public String adminEvtDetail(HttpServletRequest request, Model model, String evtNo) {
        Evt evt = eventService.findEvtByNo(evtNo);

        model.addAttribute("evt", evt);

        return "evtDetail/" + evtNo;
    }

    @RequestMapping(value = "/evtUpdate", method = RequestMethod.POST)
    public void adminEvtUpdate(@RequestBody Evt evt, HttpServletRequest request) {
        eventService.updateEvt(evt);
    }

    @RequestMapping(value = "/payList", method = RequestMethod.GET)
    public String adminPayList(HttpServletRequest request, Model model) {
        List<Pay> pays = payService.findPays();

        model.addAttribute("pays", pays);

        return "payList";
    }

    @RequestMapping(value = "/payDetail", method = RequestMethod.GET)
    public String adminPayDetail(HttpServletRequest request, Model model, String payNo) {
        Pay pay = payService.findPayByPayNo(payNo);

        model.addAttribute("pay", pay);

        return "payDetail/" + payNo;
    }

    @RequestMapping(value = "/cpnList", method = RequestMethod.GET)
    public String adminCpnList(HttpServletRequest request, Model model) {
        String period = "0";
        List<Cpn> cpns = couponService.validCpns(period);

        model.addAttribute("cpns", cpns);

        return "cpnList";
    }

    @RequestMapping(value = "/cpnDetail", method = RequestMethod.GET)
    public String adminCpnDetail(HttpServletRequest request, Model model, String cpnNo) {
        Cpn cpn = couponService.findCpnByNo(cpnNo);

        model.addAttribute("cpn", cpn);

        return "cpnDetail/" + cpnNo;
    }

    @RequestMapping(value = "/cpnUpdate", method = RequestMethod.POST)
    public void adminCpnUpdate(HttpServletRequest request, Cpn cpn) {
        String loginId = sessionManager.getSession(request).getMbrId();

        cpn.setUdterId(loginId);

        couponService.updateCpn(cpn, request);
    }
    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public String adminReviews(HttpServletRequest request, Model model) {
        List<GoodReview> reviews = goodService.findAllReviews();

        model.addAttribute("reviews", reviews);

        return "reviews";
    }

    @RequestMapping(value = "/reviewDetail", method = RequestMethod.GET)
    public String adminReviewDetail(HttpServletRequest request, Model model, String goodNo) {
        List<GoodReview> reviews = goodService.findRvByNo(goodNo);

        model.addAttribute("review", reviews);

        return "reviewDetail/" + goodNo;
    }

    @RequestMapping(value = "/reviewUpdate", method = RequestMethod.POST)
    public void adminReviewUpdate(HttpServletRequest request, GoodReview review) {
        goodService.updateReview(review);
    }

    @RequestMapping(value = "/reviewDelete", method = RequestMethod.GET)
    public String admindReviewDelete(HttpServletRequest request, Model model, String goodNo) {
        goodService.deleteReview(goodNo);
        return "reviews";
    }

    @RequestMapping(value = "/stdCtgry/list", method = RequestMethod.GET)
    public String stdCtgryList(HttpServletRequest request, Model model) {
        List<StdCtgry> stdCtgries = adminService.stdCtgryList();

        model.addAttribute("stdCtgries", stdCtgries);
        return "stdCtgry/list";
    }

    @RequestMapping(value = "/dspCtgry/list", method = RequestMethod.GET)
    public String dspCtgryList(HttpServletRequest request, Model model) {
        List<DspCtgry> dspCtgries = adminService.dspCtgryList();

        model.addAttribute("dspCtgries", dspCtgries);
        return "dspCtgry/list";
    }

    @RequestMapping(value = "/stdCtgry/delete", method = RequestMethod.POST)
    public void stdCtgryDelete(String stdCtgryNo) {
        adminService.stdCtgryDelete(stdCtgryNo);
    }

    @RequestMapping(value = "/dspCtgry/delete", method = RequestMethod.POST)
    public void dspCtgryDelete(String dspCtgryNo) {
        adminService.dspCtgryDelete(dspCtgryNo);
    }

    @RequestMapping(value = "/stdCtgry/addForm", method = RequestMethod.GET)
    public String stdCtgryAddForm() {
        return "stdCtgry/addForm";
    }

    @RequestMapping(value = "/dspCtgry/addForm", method = RequestMethod.GET)
    public String dspCtgryAddForm() {
        return "dspCtgry/addForm";
    }

    @RequestMapping(value = "/stdCtgry/add", method = RequestMethod.POST)
    public void AddstdCtgry(StdCtgry stdCtgry) {
        adminService.addStdCtgry(stdCtgry);
    }

    @RequestMapping(value = "/dspCtgry/add", method = RequestMethod.POST)
    public void AddDspCtgry(DspCtgry dspCtgry) {
        adminService.addDspCtgry(dspCtgry);
    }

    @RequestMapping(value = "/stdCtgry/update", method = RequestMethod.POST)
    public void updateStdCtgry(StdCtgry stdCtgry) {
        adminService.updateStdCtgry(stdCtgry);
    }

}
