package ru.isu.auc.scheduling.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class JobHelper {

    final
    SchedulerFactoryBean schedulerFactory;

    public JobHelper(SchedulerFactoryBean schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
    }

    private JobDetail jobDetail(String roomCode) {
        return newJob()
            .ofType(SampleJob.class)
            .storeDurably()
            .withIdentity(JobKey.jobKey("Qrtz_Job_Detail/" + roomCode))
            .withDescription("Invoke Sample Job service...")
            .build();
    }

    private Trigger trigger(JobDetail job) {
        //int frequencyInSec = 5;

        return newTrigger()
            .forJob(job)
            .withIdentity(TriggerKey.triggerKey("Qrtz_Trigger"))
            .withDescription("Sample trigger")
            //.withSchedule(simpleSchedule()
            //    .withIntervalInSeconds(frequencyInSec)
            //    .withRepeatCount(2))
            .build();
    }

    public void startJob(String roomCode) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = jobDetail(roomCode);
        Trigger trigger = trigger(jobDetail);
        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();
    }
}
