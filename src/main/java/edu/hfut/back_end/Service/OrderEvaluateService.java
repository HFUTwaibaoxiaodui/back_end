package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.ExceptionOrder;
import edu.hfut.back_end.Entity.OrderEvaluate;
import edu.hfut.back_end.Mapper.ExceptionOrderMapper;
import edu.hfut.back_end.Mapper.OrderEvaluateMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author HeRunLin
 */
@Service
public class OrderEvaluateService {
    @Resource
    OrderEvaluateMapper orderEvaluateMapper;

    public boolean addOrderEvaluate(OrderEvaluate orderEvaluate){
        return orderEvaluateMapper.addOrderEvaluate(orderEvaluate);
    }
}
