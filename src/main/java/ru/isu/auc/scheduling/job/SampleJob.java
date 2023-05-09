package ru.isu.auc.scheduling.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("fail");
        } finally {
            System.out.println("success");
        }

    }
}
