package com.github.artfultom.wexapi.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PushTradeType {

    @JsonProperty("buy")
    BUY,

    @JsonProperty("sell")
    SELL;
}
