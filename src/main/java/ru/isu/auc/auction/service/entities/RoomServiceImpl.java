package ru.isu.auc.auction.service.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.repo.RoomRepo;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    RoomRepo roomRepo;
    @Override
    public void save(Room room) {
        roomRepo.save(room);
    }
}
