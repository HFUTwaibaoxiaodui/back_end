package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.Account;
import edu.hfut.back_end.Mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountService {
    @Resource
    AccountMapper accountMapper;

    public void signIn(Account account){
        accountMapper.signIn(account);
    }
}
