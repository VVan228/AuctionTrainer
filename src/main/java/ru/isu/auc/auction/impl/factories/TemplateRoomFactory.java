package ru.isu.auc.auction.impl.factories;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.auction.model.InvalidRequestException;
import ru.isu.auc.auction.model.dto.request.CreateDefaultRoomRequest;
import ru.isu.auc.auction.model.dto.request.CreateRoomFromTemplateRequest;
import ru.isu.auc.auction.model.dto.request.RoundRequestPart;
import ru.isu.auc.auction.model.room.Lot;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.auction.model.room.Round;
import ru.isu.auc.exception.model.AbstractException;
import ru.isu.auc.security.model.User;
import ru.isu.auc.templates.api.TemplateService;
import ru.isu.auc.templates.model.Template;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateRoomFactory implements RoomFactory<CreateRoomFromTemplateRequest> {

    @Autowired
    TemplateService templateService;

    @Autowired
    RoomFactory<CreateDefaultRoomRequest> defaultRoomFactory;

    @Override
    public Triplet<Room, List<Lot>, List<Round>> createRoom(CreateRoomFromTemplateRequest request, User creator) throws AbstractException {
        CreateDefaultRoomRequest defRequest = new CreateDefaultRoomRequest();
        Template template = templateService.getSingleTemplate(request.getTemplateId(), creator.getId());
        defRequest
            .setStartTime(request.getStartTime())
            .setDefaultRoundPause(template.getData().getRoundPauseDuration())
            .setDefaultLotDuration(template.getData().getLotDuration())
            .setDefaultLotPause(template.getData().getLotPauseDuration())
            .setDefaultBetParams(template.getData().getBetParams())
            .setName(request.getName());

        if(request.getLots().size()!=template.getData().getRounds().size()) {
            throw InvalidRequestException.wrongNumberOfRounds();
        }

        List<RoundRequestPart> rounds = new ArrayList<>();
        for(int i = 0; i<request.getLots().size(); i++) {
            request.getLots().get(i).forEach(lot->
                lot.setAutoend(!template.getData().getManualMode())
                    .setAutostart(!template.getData().getManualMode()));
            rounds.add(new RoundRequestPart()
                .setLots(request.getLots().get(i))
                .setAscending(template.getData().getRounds().get(i).getAscending())
                .setRoundPause(template.getData().getRounds().get(i).getRoundPauseDuration())
                .setDefaultLotPause(template.getData().getRounds().get(i).getLotDuration())
                .setDefaultLotDuration(template.getData().getRounds().get(i).getLotPauseDuration())
                .setDefaultBetParams(template.getData().getRounds().get(i).getBetParams())
                .setAutoend(!template.getData().getManualMode())
                .setAutostart(!template.getData().getManualMode())
            );
        }
        defRequest.setRounds(rounds);

        return defaultRoomFactory.createRoom(defRequest, creator);
    }
}
