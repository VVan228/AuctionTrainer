package ru.isu.auc.auction.impl.entities;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.AuctionService;
import ru.isu.auc.auction.api.CreateRoomRequest;
import ru.isu.auc.auction.api.NotificationService;
import ru.isu.auc.auction.api.entities.*;
import ru.isu.auc.auction.api.factorties.IntervalQueueFactory;
import ru.isu.auc.auction.api.RoomFactoryProvider;
import ru.isu.auc.auction.model.EntityNotFoundException;
import ru.isu.auc.auction.model.InvalidRequestException;
import ru.isu.auc.auction.model.dto.response.*;
import ru.isu.auc.auction.model.dto.response.layers.RoomMultiLayer;
import ru.isu.auc.auction.model.dto.response.layers.RoomSingleLayer;
import ru.isu.auc.auction.model.dto.response.layers.RoundBottomLayer;
import ru.isu.auc.auction.model.interval.Interval;
import ru.isu.auc.auction.model.room.*;
import ru.isu.auc.auction.model.types.IntervalType;
import ru.isu.auc.auction.model.types.Status;
import ru.isu.auc.auction.repo.ConnectedUsersRepo;
import ru.isu.auc.auction.repo.RoomRepo;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.NotAllowedException;
import ru.isu.auc.security.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    ConnectedUsersRepo connectedUsersRepo;

    @Autowired
    LotService lotService;
    @Autowired
    RoundService roundService;
    @Autowired
    IntervalService intervalService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    RoomFactoryProvider roomFactoryProvider;
    @Autowired
    IntervalQueueFactory intervalQueueFactory;
    @Autowired
    AuctionService auctionService;
    @Autowired
    IntervalQueueService intervalQueueService;

    @Override
    public Room save(Room room) {
        return roomRepo.save(room);
    }

    @Override
    public Optional<Room> get(Long roomId) {
        return roomRepo.findById(roomId);
    }

    @Override
    public List<Room> saveAll(Iterable<Room> rooms) {
        return roomRepo.saveAll(rooms);
    }

    @Override
    @Transactional
    public <R extends CreateRoomRequest> void  createRoom(R request, User user) throws AbstractException {

        //create room
        var r = roomFactoryProvider.getFactory(request)
            .createRoom(request, user);
        //save lot entities
        lotService.saveAll(r.getValue1());
        //save round entities
        roundService.saveAll(r.getValue2());
        //save intervals
        intervalService.saveAll(r.getValue0().getIntervals());
        //update room id
        Room room = roomRepo.save(r.getValue0());

        //mark each intervalpoint's parent
        var q = intervalQueueFactory.createFromIntervals(room);
        q = intervalQueueService.save(q);
        intervalQueueFactory.saveIds(q);

        //update queue
        intervalQueueService.save(q);
        //create connected users instance
        connectedUsersRepo.save(new ConnectedUsers().setRoomId(room.getId()));
        //add room to schedule
        auctionService.scheduleRoom(room);

    }


    //TODO: cache put
    @Override
    public <R extends RoomDTO> R getFullRoom(Long roomId) throws AbstractException {
        Room room = roomRepo.findById(roomId).orElseThrow(EntityNotFoundException::room);

        //if(getRoomStatus(room).equals(Status.ONGOING))
        //    throw InvalidRequestException.roomIsAlreadyGoing();

        switch (room.getRoomType()){
            case SINGLE_LAYER -> {
                return (R) getSingleLayerRoom(room);
            }
            case MULTI_LAYER -> {
                return (R) getMultiLayerRoom(room);
            }
            default -> {
                return null;
            }
        }
    }

    private RoomMultiLayer getMultiLayerRoom(Room room) {
        //TODO: ??
        return null;
    }

    private RoomSingleLayer getSingleLayerRoom(Room room) {
        RoomSingleLayer res = new RoomSingleLayer(room, room.getStatus());


        var users = connectedUsersRepo.findById(room.getId())
                .orElse(new ConnectedUsers()).getJoinedUsers();
        res.setConnectedUsers(users.stream()
            .map(UserDTO::new).collect(Collectors.toList()));

        for(Interval i: room.getIntervals()) {
            if(i.getType()!= IntervalType.ROUND) continue;
            RoundBottomLayer round = new RoundBottomLayer(
                roundService
                    .findByUid(i.getEntityUid()),
                    i
            );

            for(Interval j: i.getIntervals()) {
                if(j.getType()!= IntervalType.LOT) continue;
                round.getLots().add(
                    new LotDTO(
                        lotService.findByUid(j.getEntityUid()),
                        j
                    )
                );
            }
            res.getRounds().add(round);
        }

        return res;
    }

    @Override
    @Transactional
    public void joinRoom(User user, Long roomId) throws AbstractException {
        Room room = roomRepo.findById(roomId).orElseThrow(EntityNotFoundException::room);

        Status roomStatus = room.getStatus();
        if(roomStatus.equals(Status.ONGOING)||roomStatus.equals(Status.ENDED))
            throw InvalidRequestException.roomIsAlreadyGoing();


        boolean inRoom = connectedUsersRepo.isInRoom(room.getId(), user.getId());
        if(inRoom) {
            throw NotAllowedException.alreadyInRoom();
        }

        var users = connectedUsersRepo.findById(roomId).orElseThrow(EntityNotFoundException::connectedUsers);
        users.getJoinedUsers().add(user);
        connectedUsersRepo.save(users);
        notificationService.sendConnectionNotification(user, room, users.getJoinedUsers().size());
    }

    @Override
    @Transactional
    public void leaveRoom(User user, Long roomId) throws AbstractException {
        Room room = roomRepo.findById(roomId).orElseThrow(EntityNotFoundException::room);

        Status roomStatus = room.getStatus();
        if(roomStatus.equals(Status.ONGOING)||roomStatus.equals(Status.ENDED))
            throw InvalidRequestException.roomIsAlreadyGoing();

        boolean inRoom = connectedUsersRepo.isInRoom(room.getId(), user.getId());
        if(!inRoom) {
            throw NotAllowedException.notInRoom();
        }

        var users = connectedUsersRepo.findById(roomId).orElseThrow(EntityNotFoundException::connectedUsers);
        users.getJoinedUsers().removeIf(userShort -> userShort.getId().equals(user.getId()));
        connectedUsersRepo.save(users);
        notificationService.sendDisconnectionNotification(user, room, users.getJoinedUsers().size());

    }

    @Override
    @Transactional
    public void handleBet(Long intervalId, User user, Long sum) throws AbstractException {
        Interval interval = intervalService.get(intervalId);
        Lot lot = lotService.findByIntervalId(intervalId);
        BetParams betParams = lot.getBetParams();
        Round round = roundService.findByLotId(lot.getId());
        Long roomId = roomRepo.getIdByLotId(lot.getId());
        boolean inRoom = connectedUsersRepo.isInRoom(roomId, user.getId());

        if(lot.getBets().stream().map(ParticipantBet::getUser)
            .anyMatch(b->b.getUsername().equals(user.getUsername()))){
            throw InvalidRequestException.alreadyMadeBet();
        }
        if(!interval.getStatus().equals(Status.ONGOING)) {
            throw InvalidRequestException.lotIsNotOngoing();
        }
        if(!inRoom) {
            throw NotAllowedException.notInRoom();
        }
        if(round.getAscending() && sum-betParams.getStartSum()<0) {
            throw InvalidRequestException.betIsTooSmall();
        }
        if(round.getAscending() && sum-betParams.getLimitSum()>0) {
            throw InvalidRequestException.betIsTooBig();
        }
        if(!round.getAscending() && sum-betParams.getStartSum()>0) {
            throw InvalidRequestException.betIsTooBig();
        }
        if(!round.getAscending() && sum-betParams.getLimitSum()<0) {
            throw InvalidRequestException.betIsTooSmall();
        }
        if(Math.abs(sum-betParams.getStartSum())<betParams.getMinBetStep()) {
            throw InvalidRequestException.betHasBadStep();
        }

        lot.getBets().add(new ParticipantBet().setSum(sum).setUser(user));
        lotService.save(lot);
    }

    @Override
    @Transactional
    public ParticipantBet getLotResultByIntervalId(Long intervalId) throws AbstractException {
        Lot lot = lotService.findByIntervalId(intervalId);
        Interval interval = intervalService.get(intervalId);
        if(interval.getStatus().equals(Status.ONGOING)){
            throw InvalidRequestException.lotIsNotEnded();
        }
        return lot.getWinner();
    }

    @Transactional
    @Override
    public ParticipantBet getLotResultByLotId(Long lotId) throws AbstractException {
        Lot lot = lotService.findById(lotId);
        Interval interval = intervalService.getByLotId(lotId);
        if(interval.getStatus().equals(Status.ONGOING)){
            throw InvalidRequestException.lotIsNotEnded();
        }
        return lot.getWinner();
    }


}
