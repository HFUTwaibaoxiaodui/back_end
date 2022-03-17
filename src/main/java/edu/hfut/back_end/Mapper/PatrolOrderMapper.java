package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.PatrolOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface PatrolOrderMapper {

    List<PatrolOrder> selectAll();

    void insert(PatrolOrder patrolOrder);

    void update(PatrolOrder patrolOrder);

    void delete(@Param("orderId") BigInteger orderId);

    List<PatrolOrder> findByOrderTitle(@Param("orderTitle") String orderTitle);

    List<PatrolOrder> findByOrderNumber(@Param("orderNumber") String orderNumber);

    List<PatrolOrder> findByOrderState(@Param("orderState") String orderState);

    PatrolOrder findByOrderId(@Param("orderId") BigInteger orderId);

    void updateOrderState(@Param("orderId") BigInteger orderId, @Param("orderState") String orderState);

}
