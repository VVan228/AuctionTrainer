package ru.isu.auc.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.isu.auc.messaging.service.MessagingService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    MessagingService messagingService;

    @ResponseBody
    @RequestMapping(
            value="/auth/test",
            method = RequestMethod.POST
    )
    public void authenticate(){
        Map<String, Object> data = new HashMap<>();
        data.put("data", "data");
        messagingService.sendNotification(data, "test");
    }
}
