package ru.isu.auc.auction.model.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationType {
    TeamConnected("teams:connected"),
    TeamUserJoined("teams:userJoined"),
    TeamCaptainChanged("teams:captainChanged"),
    UserConnected("users:connected"),
    UserDisconnected("users:disconnected"),
    RoomEvent("room:event");

    @JsonValue
    private final String alias;

    NotificationType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    @JsonCreator
    public static NotificationType fromAlias(String alias) {
        for(var type: NotificationType.values())
            if(type.getAlias().equals(alias))
                return type;
        return null;
    }
}
