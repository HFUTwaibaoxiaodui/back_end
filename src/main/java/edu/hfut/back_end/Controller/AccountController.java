package edu.hfut.back_end.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hfut.back_end.Entity.Account;
import edu.hfut.back_end.Entity.OperationLog;
import edu.hfut.back_end.Service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/login")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/userlogin",method =RequestMethod.POST)
    @ResponseBody
    String login(String username,String password) throws JsonProcessingException {
        HashMap<String,Object> hs=new HashMap<>();
        System.out.println(username+"  "+password);
        if(accountService.judgeLoad(username,password)){
            hs.put("token",username);
            hs.put("type",accountService.selectAccountType(username));
            ObjectMapper objectMapper=new ObjectMapper();
            accountService.updateLoginTime(username);
            return objectMapper.writeValueAsString(hs);
        }
        else{
            return "false";
        }
    }
    @RequestMapping(value = "/selectOneInformation",method =RequestMethod.GET)
    List<Account> selectOneInformation(NativeWebRequest webRequest){
        System.out.println(webRequest.getHeader("token"));
        System.out.println(webRequest.getParameter("token"));
        return accountService.selectOneInformation(webRequest.getHeader("token"));
    }

    @RequestMapping(value="/usersignin",method =RequestMethod.POST)
    String signIn(@RequestBody Account account){
        Date currentTime=new Date();
        account.setAccountType("USER");
        account.setAccountState("正常");
        account.setCurrentTime(currentTime);
        if(accountService.phoneIsExist(account.getPhone())){
           return "手机号重复";
        }
        else if(accountService.accountNameIsExist(account.getAccountName())){
            return "用户名重复";
        }
        else{
            accountService.signIn(account);
            return "注册成功";
        }
    }

    @RequestMapping(value = "/updateinformation",method = RequestMethod.PUT)
    void updateInformation(@RequestBody Account account){
        account.setCurrentTime(new Date());
        accountService.updateInformation(account);
    }

    @RequestMapping(value = "/findAccountNameByAccountId", method = RequestMethod.GET)
    @ApiOperation(value = "通过accountId查询accountName", notes = "通过orderId查询操作记录")
    public Account findAccountNameByAccountId(BigInteger accountId) {
        return accountService.findAccountNameByAccountId(accountId);
    }

}
