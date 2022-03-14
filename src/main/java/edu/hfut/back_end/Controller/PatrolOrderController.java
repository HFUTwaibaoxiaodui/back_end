package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.PatrolOrder;
import edu.hfut.back_end.Service.PatrolOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("开始新增工单...{}", patrolOrder);
        Date date = new Date();
        patrolOrder.setGmtCreate(date);
        patrolOrder.setGntModified(date);
        patrolOrder.setPlanStartTime(date);
        patrolOrder.setPlanEndTime(date);
        patrolOrderService.insert(patrolOrder);
    }
}
