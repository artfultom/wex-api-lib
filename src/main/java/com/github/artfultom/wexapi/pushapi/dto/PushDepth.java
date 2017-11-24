package com.github.artfultom.wexapi.pushapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PushDepth implements Serializable {

    private List<Empty> ask;

    private List<Empty> bid;

    public PushDepth() {
    }

    public List<Empty> getAsk() {
        return ask;
    }

    public void setAsk(List<Empty> ask) {
        this.ask = ask;
    }

    public List<Empty> getBid() {
        return bid;
    }

    public void setBid(List<Empty> bid) {
        this.bid = bid;
    }

    public static class Empty implements Serializable {

        private BigDecimal rate;

        private BigDecimal amount;

        public Empty() {
        }

        public Empty(BigDecimal rate, BigDecimal amount) {
            this.rate = rate;
            this.amount = amount;
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
}
