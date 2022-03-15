package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper {
    void signIn(Account account);
    String searchPassword(String username);
    void updateLoginTime(Date loginTime,String accountName);
    List<Account> selectOneInformation(String accountName);
    String selectAccountType(String accountName);
}
