package edu.hfut.back_end.Entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class PatrolOrder {

    BigInteger orderId;

    BigInteger creatorId;

    String orderTitle;

    String orderDescription;

    String orderNumber;

    String orderAddress;

    Date planStartTime;

    Date planEndTime;

    Integer score;

    Date gmtCreate;

    Date gmtModified;

}
