package com.github.artfultom.wexapi.util;

public enum TradeType {
    BUY,
    SELL;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
