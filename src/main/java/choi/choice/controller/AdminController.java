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
    private IdGenService idGenService;

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
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<Mbr> mbrs = memberService.findAll();
        List<MbrGrd> mbrGrds = new ArrayList<>();

        int mbrCnt = mbrs.size();

        for (int i = 0; i < mbrs.size(); i++) {
            mbrGrds.add(memberService.findGrdByNo(mbrs.get(i).getMbrNo()));
        }

        model.addAttribute("mbrs", mbrs);
        model.addAttribute("mbrCnt", mbrCnt);
        model.addAttribute("mbrGrds", mbrGrds);
        return "mbrList";
    }

    @RequestMapping(value = "/mbrDetail", method = RequestMethod.GET)
    public String adminMbrDetail(HttpServletRequest request, Model model, String mbrId) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        Mbr mbr = memberService.findById(mbrId);

        model.addAttribute("mbr", mbr);

        return "mbrDetail/" + mbrId;
    }

    @RequestMapping(value = "/mbrUpdate", method = RequestMethod.POST)
    public void adminMbrUpdate(HttpServletRequest request, String mbrId) {
        Mbr mbr = memberService.findById(mbrId);
        memberService.updateMbr(mbr);
    }

    @RequestMapping(value = "/addBlcklst", method = RequestMethod.POST)
    public void adminAddBlcklst(HttpServletRequest request, String mbrId) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        Mbr mbr = memberService.findById(mbrId);
        memberService.addBlcklst(mbr);
    }

    @RequestMapping(value = "/removeBlcklst", method = RequestMethod.POST)
    public void adminRemoveBlcklst(HttpServletRequest request, String mbrId) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        Mbr mbr = memberService.findById(mbrId);
        memberService.removeBlcklst(mbr);
    }

    @RequestMapping(value = "/blcklstList", method = RequestMethod.GET)
    public String adminBlcklstList(HttpServletRequest request, Model model) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<Mbr> blcklst = memberService.findBlcklstList();

        model.addAttribute("blcklst", blcklst);

        return "blcklstList";
    }

    @RequestMapping(value = "/goodsList", method = RequestMethod.GET)
    public String adminGoodsList(HttpServletRequest request, Model model) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<Good> goods = goodService.findAll();

        model.addAttribute("goods", goods);

        return "goodsList";
    }

    @RequestMapping(value = "/goodDetail", method = RequestMethod.GET)
    public String adminGoodDetail(HttpServletRequest request, Model model, String goodNo) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

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
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<Evt> evts = eventService.findAll();

        model.addAttribute("evts", evts);

        return "evtList";
    }

    @RequestMapping(value = "/evtDetail", method = RequestMethod.GET)
    public String adminEvtDetail(HttpServletRequest request, Model model, String evtNo) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

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
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<Pay> pays = payService.findPays();

        model.addAttribute("pays", pays);

        return "payList";
    }

    @RequestMapping(value = "/payDetail", method = RequestMethod.GET)
    public String adminPayDetail(HttpServletRequest request, Model model, String payNo) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        Pay pay = payService.findPayByPayNo(payNo);

        model.addAttribute("pay", pay);

        return "payDetail/" + payNo;
    }

    @RequestMapping(value = "/cpnList", method = RequestMethod.GET)
    public String adminCpnList(HttpServletRequest request, Model model) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        String period = "0";
        List<Cpn> cpns = couponService.validCpns(period);

        model.addAttribute("cpns", cpns);

        return "cpnList";
    }

    @RequestMapping(value = "/cpnDetail", method = RequestMethod.GET)
    public String adminCpnDetail(HttpServletRequest request, Model model, String cpnNo) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        Cpn cpn = couponService.findCpnByNo(cpnNo);

        model.addAttribute("cpn", cpn);

        return "cpnDetail/" + cpnNo;
    }

    @RequestMapping(value = "/cpnUpdate", method = RequestMethod.POST)
    public void adminCpnUpdate(HttpServletRequest request, Cpn cpn) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        String loginId = sessionManager.getSession(request).getMbrId();

        cpn.setUdterId(loginId);

        couponService.updateCpn(cpn, request);
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public String adminReviews(HttpServletRequest request, Model model) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<GoodReview> reviews = goodService.findAllReviews();

        model.addAttribute("reviews", reviews);

        return "reviews";
    }

    @RequestMapping(value = "/reviewDetail", method = RequestMethod.GET)
    public String adminReviewDetail(HttpServletRequest request, Model model, String goodNo) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

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
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        goodService.deleteReview(goodNo);
        return "reviews";
    }

    @RequestMapping(value = "/stdCtgry/list", method = RequestMethod.GET)
    public String stdCtgryList(HttpServletRequest request, Model model) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<StdCtgry> stdCtgries = adminService.stdCtgryList();

        model.addAttribute("stdCtgries", stdCtgries);
        return "stdCtgry/list";
    }

    @RequestMapping(value = "/dspCtgry/list", method = RequestMethod.GET)
    public String dspCtgryList(HttpServletRequest request, Model model) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

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
    public void updateStdCtgry(StdCtgry stdCtgry, HttpServletRequest request) {
        adminService.updateStdCtgry(stdCtgry, request);
    }

    @RequestMapping(value = "/dspCtgry/update", method = RequestMethod.POST)
    public void updateDspCtgry(DspCtgry dspCtgry, HttpServletRequest request) {
        adminService.updateDspCtgry(dspCtgry, request);
    }

    @RequestMapping(value = "/stdDspCtgryCnnc", method = RequestMethod.POST)
    public void stdDspCtgryCnnc(StdCtgry stdCtgry, List<DspCtgry> dspCtgryList, HttpServletRequest request) {
        adminService.cnncStdDspCtgry(stdCtgry, dspCtgryList, request);
    }

    @RequestMapping(value = "/stdDspCtgrtCnncDelete", method = RequestMethod.POST)
    public void stdDspCtgrtCnncDelete(StdDspCtgryCnnc stdDspCtgryCnnc, StdCtgry stdCtgry, DspCtgry dspCtgry) {
        adminService.stdDspCtgrtCnncDelete(stdDspCtgryCnnc, stdCtgry, dspCtgry);
    }

    @RequestMapping(value = "/sysWordDicListPage", method = RequestMethod.GET)
    public String sysWordDicListPage() {
        return "/sysWordDicList";
    }

    @RequestMapping(value = "/sysWordDicList", method = RequestMethod.GET)
    public List<SysWordDic> sysWordDicList() {
        List<SysWordDic> list = adminService.getSysWordDicList();
        return list;
    }

    @RequestMapping(value = "/sysCdAddForm", method = RequestMethod.GET)
    public String sysCdAddForm() {
        return "/sysCdAddForm";
    }

    @RequestMapping(value = "/addSysCd", method = RequestMethod.POST)
    public String addSysCd(SysCd sysCd, HttpServletRequest request) {
        adminService.addSysCd(sysCd, request);
        return "/sysCdList";
    }

    @RequestMapping(value = "/sysCd", method = RequestMethod.GET)
    public String sysCdDetail(SysCd sysCd, Model model, HttpServletRequest request) {
        SysCd sysCdDetail = adminService.getSysCdDetail(sysCd.getCd());

        model.addAttribute("sysCdDetail", sysCdDetail);
        return "/sysCd/"+sysCd.getCd()+"/detail";
    }

    @RequestMapping(value = "/sysCd/update", method = RequestMethod.POST)
    public void sysCdUpdate(SysCd sysCd, HttpServletRequest request) {

        adminService.sysCdUpdate(sysCd, request);
    }
}
