package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.ExceptionOrder;
import edu.hfut.back_end.Mapper.ExceptionOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author herunlin
 */
@Service
public class ExceptionOrderService {
    @Resource
    ExceptionOrderMapper exceptionOrderMapper;

    public boolean submitException(ExceptionOrder exceptionOrder) {
        return exceptionOrderMapper.submitException(exceptionOrder);
    }

    public Map<String, Object> getExceptionMessageById(int orderId) {
        return exceptionOrderMapper.getExceptionMessageById(orderId);
    }
}
