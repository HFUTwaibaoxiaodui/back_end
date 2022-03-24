package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.ExceptionOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 86139
 */
@Mapper
public interface ExceptionOrderMapper {

    boolean submitException(ExceptionOrder exceptionOrder);

    Map<String, Object> getExceptionMessageById(int orderId);

    boolean updateExceptionSolveState(int orderId);
}
