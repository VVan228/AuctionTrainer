package ru.isu.auc.auction.api;

import ru.isu.auc.auction.model.interval.IntervalPoint;

public interface NotificationService {
    void sendIntervalPointNotifications(IntervalPoint intervalPoint);
    void sendAutoendNotifications(IntervalPoint intervalPoint);
    void sendManualStartNotifications(IntervalPoint intervalPoint);
}
