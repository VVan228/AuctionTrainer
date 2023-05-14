package ru.isu.auc.auction.impl;

import org.springframework.stereotype.Service;
import ru.isu.auc.auction.api.TriggerCallback;

@Service
public class AuctionServiceImpl implements TriggerCallback {

    @Override
    public void triggerFired(Long queueId, Long index) {
        System.out.println(queueId + " " + index);
    }
}
