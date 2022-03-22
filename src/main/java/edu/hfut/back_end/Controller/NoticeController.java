package edu.hfut.back_end.Controller;

import edu.hfut.back_end.Entity.Notice;
import edu.hfut.back_end.Service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/Notice")
@Api(value = "通知接口", tags = "通知接口")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增", notes = "新增")
    public void insert(Notice notice) {
        log.info("新增...{}", notice);
/*        Date date = new Date();
        notice.setNoticeTime(date);*/
        noticeService.insert(notice);
    }

}
