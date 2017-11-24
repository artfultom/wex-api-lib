package com.github.artfultom.wexapi.pushapi.dto;

import com.github.artfultom.wexapi.util.TradeType;

import java.io.Serializable;
import java.math.BigDecimal;

public class PushTrade implements Serializable {

    private TradeType tradeType;

    private BigDecimal rate;

    private BigDecimal amount;

    public PushTrade() {
    }

    public PushTrade(TradeType tradeType, BigDecimal rate, BigDecimal amount) {
        this.tradeType = tradeType;
        this.rate = rate;
        this.amount = amount;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
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
