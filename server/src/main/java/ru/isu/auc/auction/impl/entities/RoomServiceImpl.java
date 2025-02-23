package ru.isu.auc.auction.impl.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.entities.*;
import ru.isu.auc.auction.model.room.*;
import ru.isu.auc.auction.repo.RoomRepo;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepo roomRepo;

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
}
