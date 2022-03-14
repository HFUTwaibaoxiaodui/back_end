package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.PatrolOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatrolOrderMapper {
    public List<PatrolOrder> selectAll();
    public void insert(PatrolOrder patrolOrder);
}
