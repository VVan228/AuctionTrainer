package ru.isu.auc.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.isu.auc.auction.repo.IntervalRepo;
import ru.isu.auc.auction.repo.NotificationRepo;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.messaging.service.MessagingService;
import ru.isu.auc.scheduling.api.SchedulerException;
import ru.isu.auc.scheduling.api.SchedulerService;
import ru.isu.auc.scheduling.impl.JobHelper;
import ru.isu.auc.security.service.UserRepo;

@Controller
public class TestController {

    @Autowired
    MessagingService messagingService;

    @Autowired
    JobHelper jobHelper;

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    RoomFactory roomFactory;

    @Autowired
    IntervalRepo intervalRepo;


    @Autowired
    UserRepo userRepo;

    @ResponseBody
    @RequestMapping(
            value="/auth/test",
            method = RequestMethod.POST
    )
    public void authenticate() throws SchedulerException {
        //jobHelper.clearHistory();
        schedulerService.startJob(228L, 228L, 5000);
    }
}
