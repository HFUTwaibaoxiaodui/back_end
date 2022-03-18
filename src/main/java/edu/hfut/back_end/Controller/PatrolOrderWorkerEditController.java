package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.PatrolOrder;
import edu.hfut.back_end.Entity.PatrolOrderWorkerEdit;
import edu.hfut.back_end.Service.AccountService;
import edu.hfut.back_end.Service.OperationLogService;
import edu.hfut.back_end.Service.PatrolOrderService;
import edu.hfut.back_end.Service.PatrolOrderWorkerEditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/PatrolOrderWorkerEdit")
@Api(value = "工单编辑接口", tags = "工单编辑接口")
public class PatrolOrderWorkerEditController {

    @Autowired
    PatrolOrderWorkerEditService patrolOrderWorkerEditService;

    @Autowired
    PatrolOrderService patrolOrderService;

    @Autowired
    OperationLogService operationLogService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查找全部工单编辑信息", notes = "查找全部工单编辑信息")
    public List<PatrolOrderWorkerEdit> selectAll() {
        List<PatrolOrderWorkerEdit> patrolOrderWorkerEditList = patrolOrderWorkerEditService.selectAll();
        log.info("查找全部工单编辑信息：{}", patrolOrderWorkerEditList);
        return patrolOrderWorkerEditList;
    }

    @RequestMapping(value = "/selectAllYesterday", method = RequestMethod.GET)
    @ApiOperation(value = "查找昨天全部工单信息", notes = "查找昨天全部工单信息")
    public List<PatrolOrder> selectAllYesterday() {
        List<PatrolOrderWorkerEdit> patrolOrderWorkerEditList = patrolOrderWorkerEditService.selectAll();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH) - 1, 0, 0, 0);
        Date date1 = calendar1.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH) - 1, 23, 59, 59);
        Date date2 = calendar2.getTime();
        List<PatrolOrderWorkerEdit> patrolOrderWorkerEdits = patrolOrderWorkerEditService.selectAll();
        patrolOrderWorkerEdits.clear();
        for (PatrolOrderWorkerEdit patrolOrderWorkerEdit : patrolOrderWorkerEditList) {
            Date date = patrolOrderWorkerEdit.getEndTime();
            if (date.after(date1) && date.before(date2)) {
                patrolOrderWorkerEdits.add(patrolOrderWorkerEdit);
            }
        }
        List<PatrolOrder> patrolOrderList = patrolOrderService.selectAll();
        patrolOrderList.clear();
        for (PatrolOrderWorkerEdit patrolOrderWorkerEdit : patrolOrderWorkerEdits) {
            if (patrolOrderList.contains(patrolOrderService.findByOrderId(patrolOrderWorkerEdit.getOrderId()))) {
                continue;
            }
            patrolOrderList.add(patrolOrderService.findByOrderId(patrolOrderWorkerEdit.getOrderId()));
        }
        for (PatrolOrder patrolOrder : patrolOrderList) {
            patrolOrder.setOperationLogList(operationLogService.findByOrderId(patrolOrder.getOrderId()));
            patrolOrder.setCreatorName(accountService.findContentByAccountId(patrolOrder.getCreatorId()).getRealName());
            patrolOrder.setPhone(accountService.findContentByAccountId(patrolOrder.getCreatorId()).getPhone());
            patrolOrder.setArea(accountService.findContentByAccountId(patrolOrder.getCreatorId()).getArea());
        }
        log.info("查找昨天全部工单信息：{}", patrolOrderList);
        return patrolOrderList;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增", notes = "新增")
    public void insert(PatrolOrderWorkerEdit patrolOrderWorkerEdit) {
        log.info("新增{}", patrolOrderWorkerEdit);
        Date date = new Date();
        patrolOrderWorkerEdit.setGmtCreate(date);
        patrolOrderWorkerEdit.setGmtModified(date);
/*        patrolOrderWorkerEdit.setBeginTime(date);
        patrolOrderWorkerEdit.setEndTime(date);*/
        patrolOrderWorkerEditService.insert(patrolOrderWorkerEdit);
    }

}
