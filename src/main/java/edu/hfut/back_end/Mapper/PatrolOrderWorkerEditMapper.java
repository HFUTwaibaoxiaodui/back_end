package edu.hfut.back_end.Mapper;

import edu.hfut.back_end.Entity.PatrolOrderWorkerEdit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatrolOrderWorkerEditMapper {

    List<PatrolOrderWorkerEdit> selectAll();

    void insert(PatrolOrderWorkerEdit patrolOrderWorkerEdit);

}
