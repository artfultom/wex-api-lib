package com.github.artfultom.wexapi.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderType {

    @JsonProperty("buy")
    BUY,

    @JsonProperty("sell")
    SELL;
}
