package ru.isu.auc.auction.impl.entities;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.AuctionService;
import ru.isu.auc.auction.api.entities.*;
import ru.isu.auc.auction.api.factorties.IntervalQueueFactory;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.auction.model.requests.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.repo.RoomRepo;
import ru.isu.auc.security.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepo roomRepo;

    @Autowired
    LotService lotService;
    @Autowired
    RoundService roundService;
    @Autowired
    IntervalService intervalService;

    @Autowired
    RoomFactory roomFactory;
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
    public void createDefaultRoom(CreateDefaultRoomRequest request, User user) {
        var r = roomFactory.createDefaultRoom(request, user);
        lotService.saveAll(r.getValue1());
        roundService.saveAll(r.getValue2());
        intervalService.saveAll(r.getValue0().getIntervals());
        roomRepo.save(r.getValue0());

        intervalQueueService.save(
            intervalQueueFactory.createFromIntervals(r.getValue0()));

        auctionService.scheduleRoom(r.getValue0());
    }


}
