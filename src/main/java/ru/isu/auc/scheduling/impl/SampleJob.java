package ru.isu.auc.scheduling.impl;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.TriggerCallback;

@Service
public class SampleJob implements Job {

    @Autowired
    TriggerCallback callback;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        callback.triggerFired(
            (Long) dataMap.get("queueId"),
            (Long) dataMap.get("index")
        );
    }
}
