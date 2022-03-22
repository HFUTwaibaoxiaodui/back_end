package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.NoticeDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface NoticeDetailMapper {

    List<NoticeDetail> findByReceiverId(@Param("receiverId") BigInteger receiverId);

}
