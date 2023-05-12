package ru.isu.auc.common.impl;

import ru.isu.auc.common.api.AbstractFactory;

import java.util.HashMap;

public class DefaultHashMap<K,V, F extends AbstractFactory<V>>
    extends HashMap<K,V>
{
    protected F factory;
    public DefaultHashMap(F factory) {
        this.factory = factory;
    }
    @Override
    public V get(Object k) {
        if(!containsKey(k)) put((K)k, factory.getNewInstance());
        return super.get(k);
    }
}