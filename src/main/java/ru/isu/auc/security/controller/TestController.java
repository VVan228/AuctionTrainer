package ru.isu.auc.security.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.isu.auc.auction.repo.NotificationRepo;
import ru.isu.auc.messaging.service.MessagingService;
import ru.isu.auc.scheduling.job.JobHelper;

@Controller
public class TestController {

    @Autowired
    MessagingService messagingService;

    @Autowired
    JobHelper jobHelper;

    @Autowired
    NotificationRepo notificationRepo;

    @ResponseBody
    @RequestMapping(
            value="/auth/test",
            method = RequestMethod.POST
    )
    public void authenticate() throws SchedulerException {
        //Map<String, Object> data = new HashMap<>();
        //data.put("data", "data");
        //messagingService.sendNotification(data, "test");

        //jobHelper.startJob("2");

        //Notification<TeamCaptainChangedPayload> not = new Notification<>();
        //not.setPayload(new TeamCaptainChangedPayload());
        //not.getPayload().setTeamId(0L);
        //not.getPayload().setNewCaptainId(1L);
        //not.getPayload().setOldCaptainId(2L);
        //not.setType(NotificationType.TeamCaptainChanged);

        System.out.println(notificationRepo.findAll());
    }
}
