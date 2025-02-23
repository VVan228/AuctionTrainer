package ru.isu.auc.auction.api;

import ru.isu.auc.auction.model.interval.IntervalPoint;
import ru.isu.auc.auction.model.room.Room;
import ru.isu.auc.security.model.User;

public interface NotificationService {
    /**
     * Основной метод для отправки уведомлений, происходит на стыке интервалов.
     * Стык может быть между manualend и manualstart, (manual или auto)start и autostart
     *
     * @param intervalPoint
     *        Точка на интервале, о событиях которой нужно уведомить
     */
    void sendIntervalPointNotifications(IntervalPoint intervalPoint);

    /**
     * Метод для отправки уведомлений о законченных интервалах, если параллельно с
     * ними есть manualend интервалы
     *
     * @param intervalPoint
     *        Точка на интервале, о событиях которой нужно уведомить
     */
    void sendAutoendNotifications(IntervalPoint intervalPoint);

    /**
     * Метод для отправки уведомлений о законченных интервалах, если параллельно с
     * ними есть autostart интервалы
     *
     * @param intervalPoint
     *        Точка на интервале, о событиях которой нужно уведомить
     */
    void sendManualStartNotifications(IntervalPoint intervalPoint);

    void sendConnectionNotification(User user, Room room, int participants);
    void sendDisconnectionNotification(User user, Room room, int participants);

    void sendManualStartRoomNotifications(IntervalPoint intervalPoint);
}
