package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {

    void insert(Notice notice);

}
