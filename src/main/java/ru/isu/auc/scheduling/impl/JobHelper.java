package ru.isu.auc.scheduling.impl;

import org.joda.time.DateTime;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.model.interval.IntervalQueue;
import ru.isu.auc.scheduling.api.SchedulerService;
import ru.isu.auc.scheduling.api.SchedulerException;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class JobHelper implements SchedulerService {

    final
    SchedulerFactoryBean schedulerFactory;

    final String TRIGGER_KEY = "IntervalTrigger";
    final String TRIGGER_GROUP = "%d";
    final String JOB_KEY = "IntervalPointJob/%d_%d";

    public JobHelper(SchedulerFactoryBean schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
    }

    private JobDetail jobDetail(Long queueId, Long index) {
        return newJob()
            .ofType(SampleJob.class)
            .storeDurably()
            .withIdentity(JobKey.jobKey(String.format(JOB_KEY, queueId, index)))
            .withDescription("Scheduled notification")
            .usingJobData("queueId", queueId)
            .usingJobData("index", index)
            .build();
    }

    private Trigger trigger(JobDetail job, DateTime dateTime, Long queueId) {
        return newTrigger()
            .forJob(job)
            .withIdentity(
                TriggerKey.triggerKey(
                    TRIGGER_KEY,
                    String.format(TRIGGER_GROUP, queueId)))
            .withDescription("Notification trigger")
            .startAt(dateTime.toDate())
            .build();
    }

    @Override
    public void startJob(Long queueId, Long index, DateTime dateTime) throws SchedulerException {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail jobDetail = jobDetail(queueId, index);
            Trigger trigger = trigger(jobDetail, dateTime, queueId);

            scheduler.scheduleJob(jobDetail, trigger);

            scheduler.start();
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void startJob(Long queueId, Long index, Integer duration) throws SchedulerException {
        startJob(queueId, index, DateTime.now().plusMillis(duration));
    }

    @Override
    public void stopJob(Long queueId) throws SchedulerException {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            scheduler.unscheduleJob(
                TriggerKey.triggerKey(
                    TRIGGER_KEY,
                    String.format(TRIGGER_GROUP, queueId)));
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
