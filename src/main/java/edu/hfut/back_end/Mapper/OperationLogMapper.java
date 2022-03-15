package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogMapper {

    void insert(OperationLog operationLog);

}
