package edu.hfut.back_end.Service;

import edu.hfut.back_end.Entity.PatrolOrder;
import edu.hfut.back_end.Mapper.PatrolOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatrolOrderService {

    @Resource
    private PatrolOrderMapper patrolOrderMapper;

    public List<PatrolOrder> selectAll() {
        return patrolOrderMapper.selectAll();
    }

    public void insert(PatrolOrder patrolOrder) {
        patrolOrderMapper.insert(patrolOrder);
    }
}
