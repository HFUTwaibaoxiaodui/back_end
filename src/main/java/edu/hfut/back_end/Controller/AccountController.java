package edu.hfut.back_end.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hfut.back_end.Entity.Account;
import edu.hfut.back_end.Service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigInteger;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/account")
public class AccountController {
    private final static int[] li_SecPosValue = { 1601, 1637, 1833, 2078, 2274,
            2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
            4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };
    private final static String[] lc_FirstLetter = { "a", "b", "c", "d", "e",
            "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "w", "x", "y", "z" };
    @Autowired
    AccountService accountService;

    @RequestMapping(value="/userlogin",method =RequestMethod.POST)
    @ApiOperation(value = "用户登录")
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
    @ApiOperation(value = "用户注册")
    String signIn(@RequestBody Account account){
        System.out.println(account);
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
    @ApiOperation(value = "用户更新自己信息")
    void updateInformation(@RequestBody Account account){
        account.setCurrentTime(new Date());
        accountService.updateInformation(account);
    }

    @RequestMapping(value = "/selectAllInformation",method = RequestMethod.GET)
    @ApiOperation(value = "查找所有用户的信息")
    List<Account> selectAllInformation(){
        List<Account> out=accountService.selectAllInformation();
        for(Account each :out){
            each.setFirstLetter(firstChineseLetterToEnglish(each.getRealName().substring(0,1)));
        }
        return out;
    }
    //取得给定汉字的首字母,即声母
    String firstChineseLetterToEnglish(String chinese){
        chinese = conversionStr(chinese, "GB2312", "ISO8859-1");
        if (chinese.length() > 1){ // 判断是不是汉字
            int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
            int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
            li_SectorCode = li_SectorCode - 160;
            li_PositionCode = li_PositionCode - 160;
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码
            if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
                for (int i = 0; i < 23; i++) {
                    if (li_SecPosCode >= li_SecPosValue[i]
                            && li_SecPosCode < li_SecPosValue[i + 1]) {
                        chinese = lc_FirstLetter[i];
                        break;
                    }
                }
            }
            else{ // 非汉字字符,如图形符号或ASCII码
                chinese = conversionStr(chinese, "ISO8859-1", "GB2312");
                chinese = chinese.substring(0, 1);
            }
        }
        return chinese;
    }
    /*字符串编码转换*/
    private static String conversionStr(String str, String charsetName,String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("字符串编码转换异常：" + ex.getMessage());
        }
        return str;
    }
}
