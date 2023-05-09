package ru.isu.auc.messaging.model.notifications;

public enum NotificationType {
    TeamConnected("teams:connected"),
    TeamUserJoined("teams:userJoined"),
    TeamCaptainChanged("teams:captainChanged"),
    UserConnected("users:connected"),
    UserDisconnected("users:disconnected"),
    RoomEvent("room:event");

    final String alias;

    NotificationType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
