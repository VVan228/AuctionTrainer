package ru.isu.auc.auction.model.notification.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RoomEventPayload.class, name = "room:event"),
    @JsonSubTypes.Type(value = UserJoinedPayload.class, name = "users:joinedRoom"),
    @JsonSubTypes.Type(value = UserLeftPayload.class, name = "users:leftRoom"),
})
public interface IPayload {
}
