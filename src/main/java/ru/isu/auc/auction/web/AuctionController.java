package ru.isu.auc.auction.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.isu.auc.auction.api.AuctionService;
import ru.isu.auc.auction.api.entities.RoomService;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.NotAllowedException;
import ru.isu.auc.security.model.SecurityUser;
import ru.isu.auc.security.model.User;

import java.util.Optional;

@Controller
public class AuctionController {

    @Autowired
    RoomService roomService;

    @Autowired
    AuctionService auctionService;

    @ResponseBody
    @RequestMapping(
        value="/auction/nextPoint",
        method = RequestMethod.POST
    )
    public void nextPoint(
        @RequestParam Long roomId)
    throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();
        Optional<Room> optRoom = roomService.get(roomId);
        Room r = optRoom.orElseThrow(EntityNotFoundException::room);
        if(!r.getCreator().equals(user))
            throw NotAllowedException.notCreatorOfTheRoom();

        auctionService.handleUserAction(r);
    }
}
