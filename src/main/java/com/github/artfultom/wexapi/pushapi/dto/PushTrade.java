package com.github.artfultom.wexapi.pushapi.dto;

import com.github.artfultom.wexapi.util.PushTradeType;

import java.io.Serializable;
import java.math.BigDecimal;

public class PushTrade implements Serializable {

    private PushTradeType pushTradeType;

    private BigDecimal rate;

    private BigDecimal amount;

    public PushTrade() {
    }

    public PushTrade(PushTradeType pushTradeType, BigDecimal rate, BigDecimal amount) {
        this.pushTradeType = pushTradeType;
        this.rate = rate;
        this.amount = amount;
    }

    public PushTradeType getPushTradeType() {
        return pushTradeType;
    }

    public void setPushTradeType(PushTradeType pushTradeType) {
        this.pushTradeType = pushTradeType;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
