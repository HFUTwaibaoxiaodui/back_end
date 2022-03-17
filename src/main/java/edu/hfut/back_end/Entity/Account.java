package edu.hfut.back_end.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Account {
    BigInteger accountId;
    @JsonProperty(value = "accountName")
    String accountName;
    String accountType;
    String imagePath;
    String password;
    String realName;
    String phone;
    String address;
    String area;
    Date currentTime;
    String accountState;

    public void setCurrentTime(Date currentTime){
        this.currentTime=currentTime;
    }
}
