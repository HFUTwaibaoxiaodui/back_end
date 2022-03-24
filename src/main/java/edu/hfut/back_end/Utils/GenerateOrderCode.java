package edu.hfut.back_end.Utils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自动生成工单编号
 * @author HeRunLin
 */
public class GenerateOrderCode implements Job {

    private static boolean start = false;

    private GenerateOrderCode()  {}

    private static void newJob() {
        System.out.println("123123");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            String rule = "5,15,25,35,45,55 * * * * ? *";
            JobDetail job = JobBuilder.newJob(GenerateOrderCode.class).build();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(rule))
                    .build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
            System.out.println("开始");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        increment++;
        System.out.println(increment);
    }

    private static class GeneratorOrderCodeInstance{
        private static final GenerateOrderCode INSTANCE = new GenerateOrderCode();
    }

    public static GenerateOrderCode getInstance() {
        return GeneratorOrderCodeInstance.INSTANCE;
    }

    private static int increment = 0;
    public static String gerOrderCode() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String count = String.format("%04d", increment);
        increment++;

        if (!start) {
            newJob();
        }

        return "O" + date + count;
    }
}
