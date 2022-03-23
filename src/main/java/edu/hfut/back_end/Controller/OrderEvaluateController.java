package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.ExceptionOrder;
import edu.hfut.back_end.Entity.OrderEvaluate;
import edu.hfut.back_end.Service.ExceptionOrderService;
import edu.hfut.back_end.Service.OrderEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HeRunLin
 */

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/evaluate")
@Api(value = "工单评价记录接口", tags = "工单评价记录接口")
public class OrderEvaluateController {
    @Autowired
    OrderEvaluateService orderEvaluateService;

    @PostMapping(value = "/addOrderEvaluate", produces="application/json; charset=UTF-8")
    @ApiOperation(value = "添加工单评价记录", notes = "添加工单评价记录")
    @ResponseBody
    public boolean addOrderEvaluate(OrderEvaluate orderEvaluate) {
        return orderEvaluateService.addOrderEvaluate(orderEvaluate);
    }

    @GetMapping(value = "/getEvaluateByOrderId", produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据工单id查询工单评价记录", notes = "根据工单id查询工单评价记录")
    @ResponseBody
    public OrderEvaluate getEvaluateByOrderId(int id) {
        return orderEvaluateService.getEvaluateByOrderId(id);
    }
}
