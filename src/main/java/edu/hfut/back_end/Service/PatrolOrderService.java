package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.PatrolOrder;
import edu.hfut.back_end.Mapper.PatrolOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PatrolOrderService {

    @Resource
    private PatrolOrderMapper patrolOrderMapper;

    public List<PatrolOrder> selectAll() {
        return patrolOrderMapper.selectAll();
    }

    public void insert(PatrolOrder patrolOrder) {
        patrolOrderMapper.insert(patrolOrder);
    }

    public void update(PatrolOrder patrolOrder) {
        patrolOrderMapper.update(patrolOrder);
    }

    public void delete(BigInteger orderId) {
        patrolOrderMapper.delete(orderId);
    }

    public List<PatrolOrder> findByOrderTitle(String orderTitle) {
        return patrolOrderMapper.findByOrderTitle(orderTitle);
    }

    public List<PatrolOrder> findByOrderNumber(String orderNumber) {
        return patrolOrderMapper.findByOrderNumber(orderNumber);
    }

    public List<PatrolOrder> findByOrderState(String orderState) {
        return patrolOrderMapper.findByOrderState(orderState);
    }

    public PatrolOrder findByOrderId(BigInteger orderId) {
        return patrolOrderMapper.findByOrderId(orderId);
    }

    public void updateOrderState(BigInteger orderId, String orderState) {
        patrolOrderMapper.updateOrderState(orderId, orderState);
    }

    public List<Map<String, Object>> findOrderCardDetail(
            String orderState,
            Integer workerId,
            Integer creatorId,
            String orderName
        ) {
        return patrolOrderMapper.findOrderCardDetail(orderState, workerId, creatorId, orderName);
    }

    public int findOrderCardDetailCount(
            String orderState,
            Integer workerId,
            Integer creatorId
    ) {
        return patrolOrderMapper.findOrderCardDetailCount(orderState, workerId, creatorId);
    }

    public int CountCurrentMonthCreatedOrderById(BigInteger accountId,Date startDate,Date endDate){
        return patrolOrderMapper.CountCurrentMonthCreatedOrderById(accountId,startDate,endDate);
    }
    public int CountCurrentMonthCreatedAndFinishedOrderById(BigInteger accountId,Date startDate, Date endDate){
        return patrolOrderMapper.CountCurrentMonthCreatedAndFinishedOrderById(accountId,startDate,endDate);
    }
    public int CountCurrentMonthExceptionOrderById(Date startDate,Date endDate){
        return patrolOrderMapper.CountCurrentMonthExceptionOrderById(startDate,endDate);
    }

    public void updateWorkerIdByOrderId(BigInteger orderId,BigInteger newWorkerId){
        patrolOrderMapper.updateWorkerIdByOrderId(orderId,newWorkerId);
    }
}
