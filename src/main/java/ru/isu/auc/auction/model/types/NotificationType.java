package ru.isu.auc.auction.model.types;

import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonValue
    public String getAlias() {
        return alias;
    }
}
