package edu.hfut.back_end.Job;

import edu.hfut.back_end.Entity.PatrolOrder;
import edu.hfut.back_end.Service.PatrolOrderService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigInteger;
import java.util.Calendar;

public class AutoCreateOrderJob extends QuartzJobBean {
    @Autowired
    PatrolOrderService patrolOrderService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        PatrolOrder patrolOrder=new PatrolOrder();
        Calendar currentTime=Calendar.getInstance();
        Calendar nextMonthTime=Calendar.getInstance();
        nextMonthTime.add(Calendar.MONTH,1);

        patrolOrder.setCreatorId(BigInteger.valueOf(1));
        patrolOrder.setOrderTitle("802机房日常巡检");
        patrolOrder.setOrderDescription("802机房日常巡检，系统自动生成");
        patrolOrder.setOrderNumber("S"+currentTime.get(Calendar.YEAR)
                +(currentTime.get(Calendar.MONTH)<9?"0"+(currentTime.get(Calendar.MONTH)+1):(currentTime.get(Calendar.MONTH)+1))
                +(currentTime.get(Calendar.DATE)<10?"0"+currentTime.get(Calendar.DATE):currentTime.get(Calendar.DATE))
                +"00001");//工单编号，规则待定
        patrolOrder.setOrderState("待抢单");
        patrolOrder.setOrderAddress("合肥工业大学科教楼B座802机房");
        patrolOrder.setPlanStartTime(currentTime.getTime());
        patrolOrder.setPlanEndTime(nextMonthTime.getTime());
        patrolOrder.setGmtCreate(currentTime.getTime());
        patrolOrder.setGmtModified(currentTime.getTime());

        patrolOrderService.insert(patrolOrder);

        patrolOrder.setOrderTitle("803机房日常巡检");
        patrolOrder.setOrderDescription("803机房日常巡检，系统自动生成");
        patrolOrder.setOrderNumber("S"+currentTime.get(Calendar.YEAR)
                +(currentTime.get(Calendar.MONTH)<9?"0"+(currentTime.get(Calendar.MONTH)+1):(currentTime.get(Calendar.MONTH)+1))
                +(currentTime.get(Calendar.DATE)<10?"0"+currentTime.get(Calendar.DATE):currentTime.get(Calendar.DATE))
                +"00002");//工单编号，规则待定
        patrolOrder.setOrderAddress("合肥工业大学科教楼B座803机房");

        patrolOrderService.insert(patrolOrder);

        patrolOrder.setOrderTitle("804机房日常巡检");
        patrolOrder.setOrderDescription("804机房日常巡检，系统自动生成");
        patrolOrder.setOrderNumber("S"+currentTime.get(Calendar.YEAR)
                +(currentTime.get(Calendar.MONTH)<9?"0"+(currentTime.get(Calendar.MONTH)+1):(currentTime.get(Calendar.MONTH)+1))
                +(currentTime.get(Calendar.DATE)<10?"0"+currentTime.get(Calendar.DATE):currentTime.get(Calendar.DATE))
                +"00003");//工单编号，规则待定
        patrolOrder.setOrderAddress("合肥工业大学科教楼B座804机房");

        patrolOrderService.insert(patrolOrder);
    }
}
