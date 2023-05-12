package ru.isu.auc.auction.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.isu.auc.auction.model.requests.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.repo.RoomRepo;
import ru.isu.auc.auction.api.factorties.RoomFactory;
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

    @ResponseBody
    @RequestMapping(
        value="/room/createDefault",
        method = RequestMethod.POST
    )
    public void createRoom(
        @RequestBody CreateDefaultRoomRequest request)
    {
        User user = SecurityUser.getCurrent().getUser();
        Room r = roomFactory.createAndSaveDefaultRoom(request, user);
        roomRepo.save(r);
    }
}
