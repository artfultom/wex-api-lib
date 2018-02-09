package com.github.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.artfultom.wexapi.util.DateDeserializer;
import com.github.artfultom.wexapi.util.OrderStatus;
import com.github.artfultom.wexapi.util.OrderType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderInfo implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("return")
    private Map<String, Order> orders;

    public OrderInfo() {
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

    public Map<String, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, Order> orders) {
        this.orders = orders;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Order implements Serializable {

        /**
         * The pair on which the order was created
         */
        @JsonProperty("pair")
        private String pair;

        /**
         * Order type, buy/sell.
         */
        @JsonProperty("type")
        private OrderType orderType;

        /**
         * The initial amount at the time of order creation.
         */
        @JsonProperty("start_amount")
        private BigDecimal startAmount;

        /**
         * The remaining amount of currency to be bought/sold.
         */
        @JsonProperty("amount")
        private BigDecimal amount;

        /**
         * Sell/Buy price.
         */
        @JsonProperty("rate")
        private BigDecimal rate;

        /**
         * The time when the order was created.
         */
        @JsonProperty("timestamp_created")
        @JsonDeserialize(using = DateDeserializer.class)
        private LocalDateTime created;

        /**
         * 0 - active, 1 - executed order, 2 - canceled, 3 - canceled, but was partially executed.
         */
        @JsonProperty("status")
        private OrderStatus status;

        public Order() {
        }

        public String getPair() {
            return pair;
        }

        public void setPair(String pair) {
            this.pair = pair;
        }

        public OrderType getOrderType() {
            return orderType;
        }

        public void setOrderType(OrderType orderType) {
            this.orderType = orderType;
        }

        public BigDecimal getStartAmount() {
            return startAmount;
        }

        public void setStartAmount(BigDecimal startAmount) {
            this.startAmount = startAmount;
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

        public LocalDateTime getCreated() {
            return created;
        }

        public void setCreated(LocalDateTime created) {
            this.created = created;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public void setStatus(OrderStatus status) {
            this.status = status;
        }
    }
}
