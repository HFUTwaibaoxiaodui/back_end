package edu.hfut.back_end.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;
}
