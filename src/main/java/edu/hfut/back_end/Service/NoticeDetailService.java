package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.NoticeDetail;
import edu.hfut.back_end.Mapper.NoticeDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class NoticeDetailService {

    @Resource
    NoticeDetailMapper noticeDetailMapper;

    public List<NoticeDetail> findByReceiverId(BigInteger receiverId) {
        return noticeDetailMapper.findByReceiverId(receiverId);
    }

}
