package choi.choice.controller;

import choi.choice.domain.Cpn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    @RequestMapping("/create")
    public String cpnCreatForm() {
        return "cpn/creat";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void cpnCreat(Cpn cpn, HttpServletRequest request) {

    }

    @RequestMapping(value = "/detail")
    public Cpn cpnDetail(Cpn cpn, Model model, HttpServletRequest request) {
        return Cpn;
    }
}
