package edu.hfut.back_end.Controller;

import cn.jpush.api.push.PushResult;
import edu.hfut.back_end.Entity.Notice;
import edu.hfut.back_end.Service.NoticeService;
import edu.hfut.back_end.Utils.Result;
import edu.hfut.back_end.Utils.SendUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/JPush")
@Api(value = "极光接口", tags = "极光接口")
public class JPushController {

    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "send", notes = "send")
    @PostMapping("/sendMessage")
    public Result sendMessage(String alias, String name, String message, BigInteger orderId) {
        PushResult result = SendUtils.SendPush(alias, name, message, orderId);
        Notice notice = new Notice();
        notice.setNoticeTime(new Date());
        notice.setNoticeDetail(message);
        notice.setReceiverId(new BigInteger(alias));
        notice.setOrderId(orderId);
        notice.setSenderName(name);
        System.out.println(notice);
        noticeService.insert(notice);
        return Result.ok("保存成功");
    }

}
