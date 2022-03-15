package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.OperationLog;
import edu.hfut.back_end.Mapper.OperationLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    public void insert(OperationLog operationLog) {
        operationLogMapper.insert(operationLog);
    }

}
