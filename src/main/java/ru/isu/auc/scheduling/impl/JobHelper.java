package ru.isu.auc.scheduling.impl;

import org.joda.time.DateTime;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import ru.isu.auc.scheduling.api.SchedulerService;
import ru.isu.auc.scheduling.api.SchedulerException;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class JobHelper implements SchedulerService {

    final
    SchedulerFactoryBean schedulerFactory;

    public JobHelper(SchedulerFactoryBean schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
    }

    private JobDetail jobDetail(Long queueId, Long index) {
        return newJob()
            .ofType(SampleJob.class)
            .storeDurably()
            .withIdentity(JobKey.jobKey("IntervalPointJob/" + queueId + "_" + index))
            .withDescription("Scheduled notification")
            .usingJobData("queueId", queueId)
            .usingJobData("index", index)
            .build();
    }

    private Trigger trigger(JobDetail job, Integer duration) {
        return newTrigger()
            .forJob(job)
            .withIdentity(TriggerKey.triggerKey("Qrtz_Trigger"))
            .withDescription("Notification trigger")
            .startAt(DateTime.now().plusMillis(duration).toDate())
            .build();
    }

    public void startJob(Long queueId, Long index, Integer duration) throws SchedulerException {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail jobDetail = jobDetail(queueId, index);
            Trigger trigger = trigger(jobDetail, duration);

            scheduler.scheduleJob(jobDetail, trigger);

            scheduler.start();
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e.getMessage(), e.getCause());
        }

    }

    public void clearHistory() throws SchedulerException {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.clear();
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e.getMessage(), e.getCause());
        }
    }
}
