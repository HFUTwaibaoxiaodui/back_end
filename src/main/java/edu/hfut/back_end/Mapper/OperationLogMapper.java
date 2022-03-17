package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface OperationLogMapper {

    void insert(OperationLog operationLog);

    List<OperationLog> findByOrderId(@Param("orderId") BigInteger orderId);

}
