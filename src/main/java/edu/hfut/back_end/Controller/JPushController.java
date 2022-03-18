package edu.hfut.back_end.Controller;

import cn.jpush.api.push.PushResult;
import edu.hfut.back_end.Utils.Result;
import edu.hfut.back_end.Utils.SendUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/JPush")
@Api(value = "极光接口", tags = "极光接口")
public class JPushController {
    @ApiOperation(value = "send", notes = "send")
    @PostMapping("/sendMessage")
    public Result sendMessage() {
        PushResult result = SendUtils.testSendPush();
        return Result.ok("保存成功");
    }
}
