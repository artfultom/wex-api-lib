package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import my.artfultom.wexapi.util.DateDeserializer;
import my.artfultom.wexapi.util.OperationType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveOrders implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("return")
    private Map<String, Order> orders;

    public ActiveOrders() {
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
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
         * The pair on which the order was created.
         */
        @JsonProperty("pair")
        private String pair;

        /**
         * Order type, buy/sell.
         */
        @JsonProperty("type")
        private OperationType orderType;

        /**
         * The amount of currency to be bought/sold.
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

        public Order() {
        }

        public String getPair() {
            return pair;
        }

        public void setPair(String pair) {
            this.pair = pair;
        }

        public OperationType getOrderType() {
            return orderType;
        }

        public void setOrderType(OperationType orderType) {
            this.orderType = orderType;
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
    }
}
