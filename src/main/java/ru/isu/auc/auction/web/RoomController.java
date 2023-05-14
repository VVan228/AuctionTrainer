package ru.isu.auc.auction.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.isu.auc.auction.model.requests.CreateDefaultRoomRequest;
import ru.isu.auc.auction.repo.IntervalRepo;
import ru.isu.auc.auction.repo.LotRepo;
import ru.isu.auc.auction.repo.RoomRepo;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.auction.repo.RoundRepo;
import ru.isu.auc.security.model.SecurityUser;
import ru.isu.auc.security.model.User;
import ru.isu.auc.security.service.UserService;

@Controller
public class RoomController {


    @Autowired
    RoomFactory roomFactory;

    @Autowired
    UserService userService;

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    RoundRepo roundRepo;

    @Autowired
    LotRepo lotRepo;

    @Autowired
    IntervalRepo intervalRepo;

    @ResponseBody
    @RequestMapping(
        value="/room/createDefault",
        method = RequestMethod.POST
    )
    public void createRoom(
        @RequestBody CreateDefaultRoomRequest request)
    {
        User user = SecurityUser.getCurrent().getUser();
        var r = roomFactory.createDefaultRoom(request, user);
        lotRepo.saveAll(r.getValue1());
        roundRepo.saveAll(r.getValue2());
        intervalRepo.saveAll(r.getValue0().getIntervals());
        roomRepo.save(r.getValue0());
    }
}
