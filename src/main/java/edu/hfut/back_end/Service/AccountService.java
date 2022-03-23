package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.Account;
import edu.hfut.back_end.Mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {
    @Resource
    AccountMapper accountMapper;

    public boolean judgeLoad(String username,String password){
        String searchPassword=accountMapper.searchPassword(username);
        if(searchPassword==null||!searchPassword.equals(password)){
            return false;
        }
        else{
            return true;
        }
    }

    public void updateLoginTime(String accountName){accountMapper.updateLoginTime(new Date(),accountName);}

    public void signIn(Account account){accountMapper.signIn(account);}

    public List<Account> selectOneInformation(String accountName){return accountMapper.selectOneInformation(accountName);}

    public String selectAccountType(String accountName){return accountMapper.selectAccountType(accountName);}

    public boolean phoneIsExist(String phone){return accountMapper.phoneIsExist(phone);}
    public boolean accountNameIsExist(String accountName){return accountMapper.accountNameIsExist(accountName);}
    public List<Account> selectAllInformation(){return accountMapper.selectAllInformation();}
    public void updateInformation(Account account){accountMapper.updateInformation(account);}
    public Account selectInformationById(BigInteger accountId){return accountMapper.selectInformationById(accountId);}
    public BigInteger selectIdByAccountName(String accountName){return accountMapper.selectIdByAccountName(accountName);}
}
