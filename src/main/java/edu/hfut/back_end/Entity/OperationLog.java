package edu.hfut.back_end.Entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class OperationLog {

    BigInteger id;

    BigInteger operatorId;

    String operationName;

    String description;

    Date operationTime;

}
