package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.PatrolOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface PatrolOrderMapper {
    public List<PatrolOrder> selectAll();
    public void insert(PatrolOrder patrolOrder);
    public void update(PatrolOrder patrolOrder);
    public void delete(@Param("orderId") BigInteger orderId);
}
