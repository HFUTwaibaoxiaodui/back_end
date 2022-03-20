package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.PatrolOrderWorkerEdit;
import edu.hfut.back_end.Mapper.PatrolOrderWorkerEditMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatrolOrderWorkerEditService {

    @Resource
    PatrolOrderWorkerEditMapper patrolOrderWorkerEditMapper;

    public List<PatrolOrderWorkerEdit> selectAll() {
        return patrolOrderWorkerEditMapper.selectAll();
    }

    public void insert(PatrolOrderWorkerEdit patrolOrderWorkerEdit) {
        patrolOrderWorkerEditMapper.insert(patrolOrderWorkerEdit);
    }

}
