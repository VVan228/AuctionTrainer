package ru.isu.auc.auction.impl.factories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isu.auc.auction.api.factorties.IntervalQueueFactory;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.common.impl.SettingHandlerImpl;
import ru.isu.auc.auction.model.dto.request.CreateDefaultRoomRequest;
import ru.isu.auc.security.model.User;

public class RoomFactoryTest {

    RoomFactory<CreateDefaultRoomRequest> roomFactory;

    IntervalQueueFactory intervalQueueFactory;

    CreateDefaultRoomRequest request;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        roomFactory = new DefaultRoomFactory(
            new IntervalFactoryImpl(),
            new SettingHandlerImpl()
        );
        intervalQueueFactory = new IntervalQueueFactoryImpl();
        String r = """
            {
              "name": "greatestRoom",
              "startTime": "2023-05-11 10:00:00",
              "defaultRoundPause": 550,
              "defaultLotPause": 1000,
              "defaultLotDuration": 15000,
              "rounds": [
                {
                  "autoend": false,
                  "ascending": true,
                  "roundPause": 500,
                  "defaultLotPause": 500,
                  "defaultLotDuration": 1400,
                  "defaultBetParams": {
                    "startSum": 400,
                    "limitSum": 800,
                    "minBetStep": 50
                  },
                  "lots":[
                    {
                      "name": "lot1",
                      "description": "desc",
                      "duration": 300,
                      "pauseAfter": 10,
                      "betParams":{
                        "startSum": 450,
                        "limitSum": 850,
                        "minBetStep": 30
                      }
                    },
                    {
                      "name": "lot2",
                      "description": "desc"
                    }
                  ]
                },
                {
                  "ascending": false,
                  "autostart": false,
                  "lots":[
                    {
                      "name": "lot3",
                      "description": "desc",
                      "duration": 302,
                      "pauseAfter": 12,
                      "betParams":{
                        "startSum": 453,
                        "limitSum": 853,
                        "minBetStep": 33
                      }
                    },
                    {
                      "name": "lot4",
                      "description": "desc"
                    }
                  ]
                }
                  ]
                }
              ]
            }
            """;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        request = objectMapper.readValue(r, CreateDefaultRoomRequest.class);
    }

    @Test
    public void test() {
        var res = roomFactory.createRoom(request, new User());
        var intervals = res.getValue0().getIntervals();
        Long id = 1L;
        for (var interval : intervals) {
            interval.setId(id++);
            var subIntervals = interval.getIntervals();
            for (var subInterval : subIntervals) {
                subInterval.setId(id++);
            }
        }
        System.out.println(res.getValue0());
        var res2 = intervalQueueFactory.createFromIntervals(res.getValue0());
        System.out.println(res2);
        Assert.isTrue(res2
            .toString().equals(
                "[ +[2, 1]-[],  +[3]-[2],  +[4]-[3],  +[5]-[4, 1←|],  +[7, 6|→]-[5],  +[8]-[7],  +[9]-[8],  +[]-[9, 6]]"));
    }
}
