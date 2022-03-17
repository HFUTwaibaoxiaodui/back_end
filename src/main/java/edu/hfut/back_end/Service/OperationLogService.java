package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.OperationLog;
import edu.hfut.back_end.Mapper.OperationLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    public void insert(OperationLog operationLog) {
        operationLogMapper.insert(operationLog);
    }

    public List<OperationLog> findByOrderId(BigInteger orderId) {
        return operationLogMapper.findByOrderId(orderId);
    }

}
