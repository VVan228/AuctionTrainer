package ru.isu.auc.auction.model.types;

public enum IntervalType {
    LOT("lot", 0),
    LOT_PAUSE("lot_pause", 1),
    ROUND("round", 2),
    ROUND_PAUSE("round_pause", 3);

    final String alias;
    final int priority;

    IntervalType(String alias, int priority) {
        this.alias = alias;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public String getAlias() {
        return alias;
    }
}
