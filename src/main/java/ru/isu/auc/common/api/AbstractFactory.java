package ru.isu.auc.common.api;

public interface AbstractFactory<T>{
    T getNewInstance();
}