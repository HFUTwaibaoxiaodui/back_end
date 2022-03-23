package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.PatrolOrder;
import edu.hfut.back_end.Entity.PreviousMonthAndCurrentMonthOrderData;
import edu.hfut.back_end.Service.AccountService;
import edu.hfut.back_end.Service.OperationLogService;
import edu.hfut.back_end.Service.PatrolOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/PatrolOrder")
@Api(value = "工单接口", tags = "工单接口")
public class PatrolOrderController {

    @Autowired
    private PatrolOrderService patrolOrderService;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查找全部工单信息", notes = "查找全部工单信息")
    public List<PatrolOrder> selectAll() {
        List<PatrolOrder> patrolOrderList = patrolOrderService.selectAll();
        for (PatrolOrder patrolOrder : patrolOrderList) {
            patrolOrder.setOperationLogList(operationLogService.findByOrderId(patrolOrder.getOrderId()));
            patrolOrder.setCreatorName(accountService.selectInformationById(patrolOrder.getCreatorId()).getRealName());
            patrolOrder.setPhone(accountService.selectInformationById(patrolOrder.getCreatorId()).getPhone());
            patrolOrder.setArea(accountService.selectInformationById(patrolOrder.getCreatorId()).getArea());
        }
        log.info("查找全部工单信息：{}", patrolOrderList);
        return patrolOrderList;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增", notes = "新增")
    public void insert(PatrolOrder patrolOrder) {
        log.info("新增{}", patrolOrder);
        Date date = new Date();
        patrolOrder.setGmtCreate(date);
        patrolOrder.setGmtModified(date);
        patrolOrderService.insert(patrolOrder);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新", notes = "更新")
    public void update(PatrolOrder patrolOrder) {
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
    public List<PatrolOrder> findByOrderTitle(String orderTitle) {
        log.info("通过工单标题{}查询", orderTitle);
        return patrolOrderService.findByOrderTitle(orderTitle);
    }

    @RequestMapping(value = "/findByOrderNumber", method = RequestMethod.GET)
    @ApiOperation(value = "通过工单编号查询工单信息", notes = "通过工单编号查询工单信息")
    public List<PatrolOrder> findByOrderNumber(String orderNumber) {
        log.info("通过工单编号{}查询", orderNumber);
        return patrolOrderService.findByOrderNumber(orderNumber);
    }

    @RequestMapping(value = "/findByOrderState", method = RequestMethod.GET)
    @ApiOperation(value = "通过工单状态查询工单信息", notes = "通过工单状态查询工单信息")
    public List<PatrolOrder> findByOrderState(String orderState) {
        log.info("通过工单状态{}查询", orderState);
        return patrolOrderService.findByOrderState(orderState);
    }

    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    @ApiOperation(value = "通过工单id查询工单信息", notes = "通过工单id查询工单信息")
    public PatrolOrder findByOrderId(BigInteger orderId) {
        log.info("通过工单id{}查询", orderId);
        PatrolOrder patrolOrder = patrolOrderService.findByOrderId(orderId);
        patrolOrder.setOperationLogList(operationLogService.findByOrderId(patrolOrder.getOrderId()));
        patrolOrder.setCreatorName(accountService.selectInformationById(patrolOrder.getCreatorId()).getRealName());
        patrolOrder.setPhone(accountService.selectInformationById(patrolOrder.getCreatorId()).getPhone());
        patrolOrder.setArea(accountService.selectInformationById(patrolOrder.getCreatorId()).getArea());
        return patrolOrder;
    }

    @RequestMapping(value = "/updateOrderState", method = RequestMethod.PUT)
    @ApiOperation(value = "更新工单状态", notes = "更新工单状态")
    public void updateOrderState(BigInteger orderId, String orderState) {
        log.info("更新工单{}状态为{}", orderId, orderState);
        patrolOrderService.updateOrderState(orderId, orderState);
    }

    @RequestMapping(value = "/selectAllNow", method = RequestMethod.GET)
    @ApiOperation(value = "查找现在全部工单信息", notes = "查找现在全部工单信息")
    public List<PatrolOrder> selectAllNow() {
        List<PatrolOrder> patrolOrderList = patrolOrderService.selectAll();
        List<PatrolOrder> resultList = patrolOrderService.selectAll();
        resultList.clear();
        for (PatrolOrder patrolOrder : patrolOrderList) {
            patrolOrder.setOperationLogList(operationLogService.findByOrderId(patrolOrder.getOrderId()));
            patrolOrder.setCreatorName(accountService.selectInformationById(patrolOrder.getCreatorId()).getRealName());
            patrolOrder.setPhone(accountService.selectInformationById(patrolOrder.getCreatorId()).getPhone());
            patrolOrder.setArea(accountService.selectInformationById(patrolOrder.getCreatorId()).getArea());
        }
        Date date = new Date();
        for (PatrolOrder patrolOrder : patrolOrderList) {
            if (date.after(patrolOrder.getPlanStartTime()) && date.before(patrolOrder.getPlanEndTime())) {
                resultList.add(patrolOrder);
            }
        }
        log.info("查找现在全部工单信息：{}", resultList);
        return resultList;
    }


    @GetMapping(value = "/findOrderCardDetail", produces="application/json; charset=UTF-8")
    @ApiOperation(value = "多条件查询工单卡片的信息", notes = "多条件查询工单卡片的信息")
    public List<Map<String, Object>> findOrderCardDetail(
            String orderState,
            Integer workerId,
            Integer creatorId,
            String orderName
    ) {
        return patrolOrderService.findOrderCardDetail(orderState, workerId, creatorId, orderName);
    }

    @GetMapping("/findOrderCardDetailCount")
    @ApiOperation(value = "多条件查询工单卡片并返回数量", notes = "多条件查询工单卡片并返回数量")
    public int findOrderCardDetailCount(
            String orderState,
            Integer workerId,
            Integer creatorId
    ) {
        return patrolOrderService.findOrderCardDetailCount(orderState, workerId, creatorId);
    }

    @GetMapping("/calculateMonthOrderData")
    @ApiOperation(value = "查询该管理人员这个月和上个月创建的工单、打分的工单和所有人员的异常工单",
            notes = "查询该管理人员这个月和上个月创建的工单、打分的工单和所有人员的异常工单")
    PreviousMonthAndCurrentMonthOrderData calculateMonthOrderData(BigInteger accountId){
        PreviousMonthAndCurrentMonthOrderData previousMonthAndCurrentMonthOrderData=new PreviousMonthAndCurrentMonthOrderData();
        Calendar previousMonthFirstDay=initDate();
        previousMonthFirstDay.add(Calendar.MONTH,-1);
        Calendar currentMonthFirstDay=initDate();
        Calendar nextMonthFirstDay=initDate();
        nextMonthFirstDay.add(Calendar.MONTH,1);

        previousMonthAndCurrentMonthOrderData.setCurrentMonthCreatedOrder(patrolOrderService.CountCurrentMonthCreatedOrderById(accountId,
                currentMonthFirstDay.getTime(),nextMonthFirstDay.getTime()));

        previousMonthAndCurrentMonthOrderData.setCurrentMonthFinishOrder(
                patrolOrderService.CountCurrentMonthCreatedAndFinishedOrderById(accountId,
                        currentMonthFirstDay.getTime(),nextMonthFirstDay.getTime()));

        previousMonthAndCurrentMonthOrderData.setCurrentMonthExceptionOrder(
                patrolOrderService.CountCurrentMonthExceptionOrderById(currentMonthFirstDay.getTime()
                        ,nextMonthFirstDay.getTime()));

        previousMonthAndCurrentMonthOrderData.setPreviousMonthCreatedOrder(patrolOrderService.CountCurrentMonthCreatedOrderById(accountId,
                previousMonthFirstDay.getTime(),currentMonthFirstDay.getTime()));

        previousMonthAndCurrentMonthOrderData.setPreviousMonthFinishOrder(
                patrolOrderService.CountCurrentMonthCreatedAndFinishedOrderById(accountId,
                        previousMonthFirstDay.getTime(),currentMonthFirstDay.getTime()));

        previousMonthAndCurrentMonthOrderData.setPreviousMonthExceptionOrder(
                patrolOrderService.CountCurrentMonthExceptionOrderById(previousMonthFirstDay.getTime()
                        ,currentMonthFirstDay.getTime()));
        System.out.println(previousMonthAndCurrentMonthOrderData);
        return previousMonthAndCurrentMonthOrderData;
    }

    Calendar initDate(){
        Calendar currentTime=Calendar.getInstance();
        //将小时至0
        currentTime.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        currentTime.set(Calendar.MINUTE, 0);
        //将秒至0
        currentTime.set(Calendar.SECOND,0);
        //将毫秒至0
        currentTime.set(Calendar.MILLISECOND, 0);
        currentTime.set(Calendar.DAY_OF_MONTH,1);
        return currentTime;
    }
}
