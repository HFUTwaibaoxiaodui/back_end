package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.Notice;
import edu.hfut.back_end.Mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    public void insert(Notice notice) {
        noticeMapper.insert(notice);
    }

}
