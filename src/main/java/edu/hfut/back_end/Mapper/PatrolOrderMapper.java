package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.PatrolOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface PatrolOrderMapper {

    List<PatrolOrder> selectAll();

    void insert(PatrolOrder patrolOrder);

    void update(PatrolOrder patrolOrder);

    void updateWorker(Integer workerId, Integer orderId);

    void delete(@Param("orderId") BigInteger orderId);

    List<PatrolOrder> findByOrderTitle(@Param("orderTitle") String orderTitle);

    List<PatrolOrder> findByOrderNumber(@Param("orderNumber") String orderNumber);

    List<PatrolOrder> findByOrderState(@Param("orderState") String orderState);

    PatrolOrder findByOrderId(@Param("orderId") BigInteger orderId);

    void updateOrderState(@Param("orderId") BigInteger orderId, @Param("orderState") String orderState);

    /**
     * 多条件查询工单卡片的信息
     * @param orderState 工单状态
     * @param workerId 巡检人员Id
     * @param creatorId 巡检工单创建者Id
     * @param orderName 工单名称（模糊）
     * @return 工单卡片的信息
     */
    List<Map<String, Object>> findOrderCardDetail(
            String orderState,
            Integer workerId,
            Integer creatorId,
            String orderName
        );

    /**
     * 多条件查询工单并返回数量
     * @param orderState 工单状态
     * @param workerId 巡检人员Id
     * @param creatorId 巡检工单创建者Id
     * @return 满足条件的工单卡片的数量
     */
    int findOrderCardDetailCount(
            String orderState,
            Integer workerId,
            Integer creatorId
    );
    int CountCurrentMonthCreatedOrderById(BigInteger accountId,Date startDate, Date endDate);
    int CountCurrentMonthCreatedAndFinishedOrderById(BigInteger accountId,Date startDate, Date endDate);
    int CountCurrentMonthExceptionOrderById(Date startDate, Date endDate);
    void updateWorkerIdByOrderId(BigInteger orderId,BigInteger newWorkerId);
}
