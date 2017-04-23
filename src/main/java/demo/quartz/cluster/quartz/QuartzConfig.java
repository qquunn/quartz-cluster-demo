package demo.quartz.cluster.quartz;

import demo.quartz.cluster.Utils;
import org.quartz.JobDetail;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.text.ParseException;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by llq on 2016/10/28.
 */
@Configurable
public class QuartzConfig {

    @Autowired
    @Bean
    public SchedulerFactoryBean SchedulerFactoryBean(DataSource dataSource) throws ParseException {

        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setDataSource(dataSource);

        bean.setQuartzProperties(Utils.readConfig("quartz.properties"));
        bean.setSchedulerName("CRMscheduler");
        bean.setStartupDelay(10);
        bean.setApplicationContextSchedulerContextKey("applicationContextKey");
        bean.setOverwriteExistingJobs(true);
        bean.setAutoStartup(true);

        JobDetail job = newJob(TaskA.class)
                .withDescription("任务描述")
                .withIdentity("job1", "group1")
                .storeDurably()
                .build();

        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setName("trigger1");
        cronTrigger.setGroup("group1");
        // 每隔几秒执行一次
        cronTrigger.setCronExpression("*/5 * * * * ?");
        cronTrigger.setJobKey(job.getKey());

        bean.setTriggers(cronTrigger);
        bean.setJobDetails(job);

        return bean;
    }

}
