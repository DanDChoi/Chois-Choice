package choi.choice.controller;

import choi.choice.domain.Good;
import choi.choice.domain.GoodReview;
import choi.choice.repository.GoodRepository;
import choi.choice.service.GoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/good")
@RequiredArgsConstructor
public class ProductsController {

    private final GoodService goodService;
    private final GoodRepository goodRepository;

    @GetMapping("create")
    public String createGoodForm() {
        return "good/createForm";
    }

    @PostMapping("create/add")
    public String createGood(@ModelAttribute Good good, HttpServletRequest request) {
        goodService.add(good, request);
        return "ok";
    }

    @GetMapping("search")
    public String searchGood(String goodNo) {
        goodService.findByNo(goodNo);
        return "ok";
    }

    @GetMapping("delete")
    public void deleteGood(String goodNo) {
        goodService.deleteByNo(goodNo);
    }

    @GetMapping("detail")
    public String detailGood(String goodNo, Model model) {
        Good good = goodService.findByNo(goodNo);
        GoodReview review = goodService.findRvByNo(goodNo);

        model.addAttribute("good", good);
        model.addAttribute("review", review);
        return "good/detail?goodNo=" + goodNo ;
    }
}
