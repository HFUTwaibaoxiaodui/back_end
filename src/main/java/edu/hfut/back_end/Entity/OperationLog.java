package edu.hfut.back_end.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class OperationLog {

    BigInteger id;

    BigInteger orderId;

    String operationName;

    String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date operationTime;

}
