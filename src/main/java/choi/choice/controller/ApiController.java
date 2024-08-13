package choi.choice.controller;

import choi.choice.result.ProductResult;
import choi.choice.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ApiController {

    @Autowired
    private GoodService goodService;

    public ProductResult insertProductPartmal(){
        return null;
    }
}
