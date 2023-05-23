package ru.isu.auc.auction.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import ru.isu.auc.auction.api.CreateRoomRequest;
import ru.isu.auc.auction.api.factorties.RoomFactory;
import ru.isu.auc.auction.api.RoomFactoryProvider;

@Component
public class RoomFactoryProviderImpl implements RoomFactoryProvider {

    @Autowired
    ApplicationContext context;

    @Override
    public <R extends CreateRoomRequest> RoomFactory<R> getFactory(R request) {
        String[] beanNamesForType =
            context.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(RoomFactory.class, request.getClass()));

        return (RoomFactory<R>) context.getBean(beanNamesForType[0]);
    }
}
