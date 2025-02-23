package ru.isu.auc.auction.model.types;

public enum Status {
    SAVED("saved"),
    SCHEDULED("scheduled"),
    ONGOING("ongoing"),
    ENDED("ended");

    final String alias;

    Status(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
