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

}
