package ru.isu.auc.auction.impl;

import org.springframework.stereotype.Component;
import ru.isu.auc.auction.api.ChannelProvider;

@Component
public class ChannelProviderImpl implements ChannelProvider {

    //TODO: bring back
    @Override
    public String getChannel(Long roomId, boolean isAdmin) {
        //if(isAdmin)
        //    return String.format("room:%d:admin", roomId);
        //return String.format("room:%d", roomId);
        return "test";
    }
}
