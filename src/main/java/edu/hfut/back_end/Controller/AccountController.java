package edu.hfut.back_end.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hfut.back_end.Entity.Account;
import edu.hfut.back_end.Service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.reflect.Method;
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
    @RequestMapping(value = "/selectInformation",method =RequestMethod.GET)
    List<Account> selectInformation(NativeWebRequest webRequest){
        System.out.println(webRequest.getHeader("token"));
        System.out.println(webRequest.getParameter("token"));
        return accountService.selectOneInformation(webRequest.getParameter("token"));
    }

    @RequestMapping(value="/usersignin",method =RequestMethod.GET)
    void signIn(Account account){
        Date currentTime=new Date();
        account.setCurrentTime(currentTime);
        accountService.signIn(account);
    }
}
