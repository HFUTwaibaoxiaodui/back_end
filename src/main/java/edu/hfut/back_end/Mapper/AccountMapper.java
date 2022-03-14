package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    void signIn(Account account);
    String searchPassword(String username);
}
