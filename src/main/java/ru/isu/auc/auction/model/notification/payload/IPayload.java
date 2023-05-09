package ru.isu.auc.auction.model.notification.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RoomEventPayload.class, name = "room:event"),
    @JsonSubTypes.Type(value = TeamCaptainChangedPayload.class, name = "teams:captainChanged"),
    @JsonSubTypes.Type(value = TeamConnectedPayload.class, name = "teams:connected"),
    @JsonSubTypes.Type(value = TeamUserJoinedPayload.class, name = "teams:userJoined"),
    @JsonSubTypes.Type(value = UserPayload.class, name = "users:connected"),
    @JsonSubTypes.Type(value = UserPayload.class, name = "users:disconnected")
})
public interface IPayload {
}
