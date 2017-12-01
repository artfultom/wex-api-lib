package com.github.artfultom.wexapi.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TradeType {

    @JsonProperty("buy")
    BUY,

    @JsonProperty("sell")
    SELL;
}
