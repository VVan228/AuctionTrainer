package ru.isu.auc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.SecurityUser;
import ru.isu.auc.security.model.User;
import ru.isu.auc.templates.model.dto.response.TemplateDTO;

import java.util.Optional;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping(
        value="/test",
        method = RequestMethod.GET
    )
    public String test() {
        return "hello world!";
    }
}
