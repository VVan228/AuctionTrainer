package ru.isu.auc.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {


    @ResponseBody
    @RequestMapping(
            value="/auth/test",
            method = RequestMethod.POST
    )
    public String authenticate(){
        return "hi!";
    }
}
