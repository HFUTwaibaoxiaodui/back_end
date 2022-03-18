package edu.hfut.back_end.Entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author herunlin
 */
@Data
public class ExceptionOrder {
    /**
     * 工单id
     */
    private int orderId;

    /**
     * 异常类别
     */
    private String exceptionClass;

    /**
     * 异常描述
     */
    private String exceptionDetail;

    /**
     * 异常上报时间
     */
    private Date submitTime;
}
