package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.NoticeDetail;
import edu.hfut.back_end.Service.NoticeDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/NoticeDetail")
@Api(value = "通知视图接口", tags = "通知视图接口")
public class NoticeDetailController {

    @Autowired
    NoticeDetailService noticeDetailService;

    @RequestMapping(value = "/findByReceiverId", method = RequestMethod.GET)
    @ApiOperation(value = "通过receiverId查询通知视图", notes = "通过receiverId查询通知视图")
    public List<NoticeDetail> findByReceiverId(BigInteger receiverId) {
        log.info("通过{}查询通知视图", receiverId);
        return noticeDetailService.findByReceiverId(receiverId);
    }

}
