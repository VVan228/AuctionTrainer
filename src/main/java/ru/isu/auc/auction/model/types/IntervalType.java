package ru.isu.auc.auction.model.types;

public enum IntervalType {
    LOT("lot"),
    LOT_PAUSE("lot_pause"),
    ROUND("round"),
    ROUND_PAUSE("round_pause");

    final String alias;

    IntervalType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
