package choi.choice.controller;

import choi.choice.domain.ProductSDO;
import choi.choice.domain.SystemPK;
import choi.choice.result.ProductResult;
import choi.choice.service.GoodService;
import choi.choice.service.IdGenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ApiController {

    @Autowired
    private GoodService goodService;

    @RequestMapping(value = "/Product-Add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "상품등록")
    public ProductResult insertProductPartmal(@RequestHeader(value = "KEY", required = true)
                                              @Parameter(in = ParameterIn.HEADER, name = "KEY") String key,
                                              @RequestBody ProductSDO productSDO, HttpServletRequest request) throws Exception {

        return null;
    }
}
