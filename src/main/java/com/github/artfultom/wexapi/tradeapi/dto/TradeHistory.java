package com.github.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.artfultom.wexapi.util.BooleanDeserializer;
import com.github.artfultom.wexapi.util.DateDeserializer;
import com.github.artfultom.wexapi.util.PushTradeType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeHistory implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("return")
    private Map<String, Trade> trades;

    public TradeHistory() {
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, Trade> getTrades() {
        return trades;
    }

    public void setTrades(Map<String, Trade> trades) {
        this.trades = trades;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Trade implements Serializable {

        /**
         * The pair on which the trade was executed.
         */
        @JsonProperty("pair")
        private String pair;

        /**
         * Trade type, buy/sell.
         */
        @JsonProperty("type")
        private PushTradeType pushTradeType;

        /**
         * The amount of currency was bought/sold.
         */
        @JsonProperty("amount")
        private BigDecimal amount;

        /**
         * Sell/Buy price.
         */
        @JsonProperty("rate")
        private BigDecimal rate;

        /**
         * Order ID.
         */
        @JsonProperty("order_id")
        private long orderId;

        /**
         * Is equal to 1 if order_id is your order, otherwise is equal to 0.
         */
        @JsonProperty("is_your_order")
        @JsonDeserialize(using = BooleanDeserializer.class)
        private boolean isYourOrder;

        /**
         * Trade execution time.
         */
        @JsonProperty("timestamp")
        @JsonDeserialize(using = DateDeserializer.class)
        private LocalDateTime timestamp;

        public Trade() {
        }

        public String getPair() {
            return pair;
        }

        public void setPair(String pair) {
            this.pair = pair;
        }

        public PushTradeType getPushTradeType() {
            return pushTradeType;
        }

        public void setPushTradeType(PushTradeType pushTradeType) {
            this.pushTradeType = pushTradeType;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public boolean getIsYourOrder() {
            return isYourOrder;
        }

        public void setIsYourOrder(boolean isYourOrder) {
            this.isYourOrder = isYourOrder;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
}
