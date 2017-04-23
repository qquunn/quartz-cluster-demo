package demo.quartz.cluster.quartz;

import demo.quartz.cluster.Utils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TaskA implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("开始执行任务 "  + Utils.formatDate(context.getFireTime()));
    }

}

