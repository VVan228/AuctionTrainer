package ru.isu.auc.auction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.auc.auction.model.Notification;
import ru.isu.auc.auction.model.notification.payload.IPayload;

public interface NotificationRepo extends JpaRepository<Notification<? extends IPayload>, Long> {
}
