package edu.hfut.back_end.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class PatrolOrder {

    BigInteger orderId;

    BigInteger creatorId;

    String creatorName;

    String orderTitle;

    String orderDescription;

    String orderNumber;

    String orderState;

    String orderAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date planStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date planEndTime;

    Integer score;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date gmtModified;

    List<OperationLog> operationLogList;

}
