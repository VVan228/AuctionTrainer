package ru.isu.auc.auction.model.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import ru.isu.auc.auction.model.notification.payload.IPayload;
import ru.isu.auc.auction.model.types.NotificationType;

@Entity
@Data
public class Notification <P extends IPayload> {

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    P payload;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
}
