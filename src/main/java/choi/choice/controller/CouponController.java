package choi.choice.controller;

import choi.choice.domain.Cpn;
import choi.choice.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    @Autowired
    private final CouponService couponService;
    @RequestMapping("/create")
    public String cpnCreatForm() {
        return "cpn/creat";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void cpnCreat(Cpn cpn, HttpServletRequest request) {
//        couponService.
    }

    @RequestMapping(value = "/detail")
    public Cpn cpnDetail(@RequestParam("cpnNo") String cpnNo,  Cpn cpn, Model model, HttpServletRequest request) {
        Cpn cpn = couponService.couponDetail(cpnNo);
        return Cpn;
    }

    @RequestMapping(value = "/list")
    public List<Cpn> cpns() {
        return couponService.cpnList();
    }
}
