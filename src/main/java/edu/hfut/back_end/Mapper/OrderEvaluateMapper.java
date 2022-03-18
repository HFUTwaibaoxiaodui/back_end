package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.OrderEvaluate;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HeRunLin
 */
@Mapper
public interface OrderEvaluateMapper {
    boolean addOrderEvaluate(OrderEvaluate orderEvaluate);
}
