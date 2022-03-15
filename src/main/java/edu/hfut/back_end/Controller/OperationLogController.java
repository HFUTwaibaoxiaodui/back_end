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

import java.util.Date;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/OperationLog")
@Api(value = "人员操作记录接口", tags = "人员操作记录接口" )
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增", notes = "新增")
    public void insert(OperationLog operationLog) {
        log.info("开始新增...{}", operationLog);
        Date date = new Date();
        operationLog.setOperationTime(date);
        operationLogService.insert(operationLog);
    }

}
