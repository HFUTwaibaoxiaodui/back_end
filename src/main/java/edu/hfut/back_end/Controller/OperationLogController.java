package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.OperationLog;
import edu.hfut.back_end.Service.OperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/OperationLog")
@Api(value = "人员操作记录接口", tags = "人员操作记录接口")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增", notes = "新增")
    public void insert(OperationLog operationLog) {
        log.info("新增...{}", operationLog);
/*        Date date = new Date();
        operationLog.setOperationTime(date);*/
        operationLogService.insert(operationLog);
    }

    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    @ApiOperation(value = "通过orderId查询操作记录", notes = "通过orderId查询操作记录")
    public List<OperationLog> findByOrderId(BigInteger orderId) {
        log.info("通过{}查询操作记录", orderId);
        return operationLogService.findByOrderId(orderId);
    }

}
