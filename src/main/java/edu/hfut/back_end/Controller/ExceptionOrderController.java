package edu.hfut.back_end.Controller;
import edu.hfut.back_end.Entity.ExceptionOrder;
import edu.hfut.back_end.Service.ExceptionOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author herunlin
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/exception")
@Api(value = "异常上报接口", tags = "异常上报接口")
public class ExceptionOrderController {

    @Autowired
    ExceptionOrderService exceptionOrderService;

    @PostMapping(value = "/submitException", produces="application/json; charset=UTF-8")
    @ApiOperation(value = "异常上报接口", notes = "异常上报接口")
    @ResponseBody
    public boolean pageFindProject(ExceptionOrder exceptionOrder) {
        return exceptionOrderService.submitException(exceptionOrder);
    }

    @GetMapping(value = "/getExceptionMessageById", produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据工单id获取异常信息", notes = "根据工单id获取异常信息")
    @ResponseBody
    public Map<String, Object> getExceptionMessageById(int orderId) {
        return exceptionOrderService.getExceptionMessageById(orderId);
    }
}
