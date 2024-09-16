package choi.choice.controller;

import choi.choice.domain.Cpn;
import choi.choice.domain.SystemPK;
import choi.choice.service.CouponService;
import choi.choice.service.IdGenService;
import choi.choice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;
    private final SessionManager sessionManager;
    IdGenService idGenService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String cpnCreatForm() {
        return "cpn/creat";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void cpnCreat(Cpn cpn, HttpServletRequest request) {
        couponService.createCoupon(cpn);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String cpnDetail(@RequestParam("cpnNo") String cpnNo, Model model, HttpServletRequest request) {
        String loginId = sessionManager.getSession(request).getMbrId();
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        Cpn cpn = couponService.couponDetail(cpnNo);
        model.addAttribute("cpn", cpn);
        model.addAttribute("loginId", loginId);
        return "detail/" + cpnNo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String cpnUpdate(Cpn cpn, Model model, HttpServletRequest request) {
        String loginId = sessionManager.getSession(request).getMbrId();
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        cpn.setUdterId(loginId);
        couponService.updateCpn(cpn, request);

        return "dateil/" + cpn.getCpnNo();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String cpns(@RequestParam("period") String period, Model model) {

        if (period == null || period.isEmpty()){
            period = "0";
        }
        List<Cpn> cpns = couponService.cpnList(period);
        model.addAttribute("cpns", cpns);
        return "/list";
    }

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public void executeCpn(String cpnNo) {
        couponService.executeCpn(cpnNo);
    }
}
