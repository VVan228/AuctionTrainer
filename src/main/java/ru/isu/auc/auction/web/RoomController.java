package ru.isu.auc.auction.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.isu.auc.auction.api.entities.RoomService;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.auction.model.InvalidRequestException;
import ru.isu.auc.auction.model.dto.request.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.dto.request.CreateRoomFromTemplateRequest;
import ru.isu.auc.auction.model.dto.response.ParticipantBetDTO;
import ru.isu.auc.auction.model.dto.response.RoomDTO;
import ru.isu.auc.auction.model.room.ParticipantBet;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.SecurityUser;
import ru.isu.auc.security.model.User;

import java.util.Optional;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @ResponseBody
    @RequestMapping(
        value="/room/createDefault",
        method = RequestMethod.POST
    )
    public void createRoom(
        @RequestBody CreateDefaultRoomRequest request) throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();
        roomService.createRoom(request, user);
    }

    @ResponseBody
    @RequestMapping(
        value="/room/createFromTemplate",
        method = RequestMethod.POST
    )
    public void createRoomFromTemplate(
        @RequestBody CreateRoomFromTemplateRequest request) throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();
        roomService.createRoom(request, user);
    }

    @ResponseBody
    @RequestMapping(
        value="/room/get",
        method = RequestMethod.GET
    )
    public <R extends RoomDTO> R getRoom(
        @RequestParam Long roomId)
    throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();
        return roomService.getFullRoom(roomId);
    }

    @ResponseBody
    @RequestMapping(
        value="/room/join",
        method = RequestMethod.GET
    )
    public void joinRoom(
        @RequestParam Long roomId)
        throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();

        roomService.joinRoom(user, roomId);
    }

    @ResponseBody
    @RequestMapping(
        value="/room/leave",
        method = RequestMethod.GET
    )
    public void leaveRoom(
        @RequestParam Long roomId)
        throws AbstractException {
        User user = SecurityUser.getCurrent().getUser();

        roomService.leaveRoom(user, roomId);
    }

    @ResponseBody
    @RequestMapping(
        value="/room/makeBet",
        method = RequestMethod.POST
    )
    public void makeBet(
        @RequestParam Long intervalId,
        @RequestParam Long sum)
        throws AbstractException {

        User user = SecurityUser.getCurrent().getUser();

        roomService.handleBet(intervalId, user, sum);
    }

    @ResponseBody
    @RequestMapping(
        value="/room/getResult",
        method = RequestMethod.GET
    )
    public ParticipantBetDTO getLotResult(
        @RequestParam Optional<Long> intervalId,
        @RequestParam Optional<Long> lotId)
        throws AbstractException {

        if(intervalId.isEmpty() && lotId.isEmpty())
            throw InvalidRequestException.noId();

        if(lotId.isPresent()){
            var res = roomService.getLotResultByLotId(lotId.get());
            if(res!=null) {
                return new ParticipantBetDTO(res);
            }
            return null;
        }
        var res = roomService.getLotResultByIntervalId(intervalId.get());
        if(res!=null) {
            return new ParticipantBetDTO(res);
        }
        return null;
    }
}
