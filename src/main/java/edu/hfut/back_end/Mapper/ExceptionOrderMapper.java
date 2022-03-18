package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.ExceptionOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86139
 */
@Mapper
public interface ExceptionOrderMapper {

    boolean submitException(ExceptionOrder exceptionOrder);
}
