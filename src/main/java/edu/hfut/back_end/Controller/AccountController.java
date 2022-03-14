package edu.hfut.back_end.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hfut.back_end.Entity.Account;
import edu.hfut.back_end.Service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
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
        hs.put("token",username+","+password);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }

    @RequestMapping(value="/usersignin",method =RequestMethod.GET)
    void signIn(Account account){
        Date currentTime=new Date();
        account.setCurrentTime(currentTime);
        accountService.signIn(account);
    }
}
