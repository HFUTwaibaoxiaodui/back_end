package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.ExceptionOrder;
import edu.hfut.back_end.Service.ExceptionOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/login")
public class ExceptionOrderController {

    @Autowired
    ExceptionOrderService exceptionOrderService;

    @PostMapping(value = "/submitException", produces="application/json; charset=UTF-8")
    @ApiOperation(value = "上报工单异常", notes = "上报工单异常")
    @ResponseBody
    public boolean pageFindProject(ExceptionOrder exceptionOrder) {
        return exceptionOrderService.submitException(exceptionOrder);
    }
}
