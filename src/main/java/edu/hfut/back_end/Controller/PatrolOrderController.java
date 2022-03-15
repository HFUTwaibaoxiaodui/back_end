package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.PatrolOrder;
import edu.hfut.back_end.Service.PatrolOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/PatrolOrder")
@Api(value = "工单接口", tags = "工单接口" )
public class PatrolOrderController {

    @Autowired
    private PatrolOrderService patrolOrderService;

    @RequestMapping(value="/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查找全部工单信息", notes = "查找全部工单信息")
    public List<PatrolOrder> selectAll() {
        List<PatrolOrder> list = patrolOrderService.selectAll();
        log.info("查找全部工单信息：{}", list);
        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增", notes = "新增")
    public void insert(PatrolOrder patrolOrder) {
        log.info("新增{}", patrolOrder);
        Date date = new Date();
        patrolOrder.setGmtCreate(date);
        patrolOrder.setGmtModified(date);
        patrolOrder.setPlanStartTime(date);
        patrolOrder.setPlanEndTime(date);
        patrolOrderService.insert(patrolOrder);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新", notes = "更新")
    public void updateUser(PatrolOrder patrolOrder) {
        log.info("更新{}", patrolOrder);
        Date date = new Date();
        patrolOrder.setGmtModified(date);
        patrolOrder.setPlanStartTime(date);
        patrolOrder.setPlanEndTime(date);
        patrolOrderService.update(patrolOrder);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除", notes = "删除")
    public void delete(@RequestParam(value = "orderId", required = true) BigInteger orderId) {
        log.info("删除orderId={}", orderId);
        patrolOrderService.delete(orderId);
    }

    @RequestMapping(value = "/findByOrderTitle", method = RequestMethod.GET)
    @ApiOperation(value = "通过工单标题查询工单信息", notes = "通过工单标题查询工单信息")
    public List<PatrolOrder> findByName(String orderTitle) {
        log.info("通过工单标题{}查询", orderTitle);
        return patrolOrderService.findByOrderTitle(orderTitle);
    }

}
