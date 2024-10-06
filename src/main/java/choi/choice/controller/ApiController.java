package choi.choice.controller;

import choi.choice.domain.InterfaceHistory;
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

    private IdGenService idGenService;

    @RequestMapping(value = "/Product-Add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "상품등록")
    public ProductResult insertProduct(@RequestHeader(value = "KEY", required = true)
                                              @Parameter(in = ParameterIn.HEADER, name = "KEY") String key,
                                              @RequestBody ProductSDO productSDO, HttpServletRequest request) throws Exception {

        ProductResult productResult = new ProductResult();
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
        InterfaceHistory interfaceHistory = null;

        try {
            interfaceHistory.setCallClassId(request.getParameter(ParameterIn.HEADER.toString()));
        } catch (Exception e) {

        }

        return null;
    }

    @RequestMapping(value = "/Product-Update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "상품수정")
    public ProductResult updateProduct(@RequestHeader(value = "KEY", required = true)
                                       @Parameter(in = ParameterIn.HEADER, name = "KEY") String key,
                                       @RequestBody ProductSDO productSDO, HttpServletRequest request) throws Exception {

        ProductResult productResult = new ProductResult();
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
        InterfaceHistory interfaceHistory = null;

        try {
//            interfaceHistory.setCallClassId();
        } catch (Exception e) {

        }

        return null;
    }
}
