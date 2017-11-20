package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import my.artfultom.wexapi.util.OperationType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderInfo implements Serializable {

    @JsonProperty("success")
    private int success;

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

    public Map<String, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, Order> orders) {
        this.orders = orders;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Order implements Serializable {

        @JsonProperty("pair")
        private String pair;

        @JsonProperty("type")
        private OperationType type;

        @JsonProperty("start_amount")
        private BigDecimal startAmount;

        @JsonProperty("amount")
        private BigDecimal amount;

        @JsonProperty("rate")
        private BigDecimal rate;

        @JsonProperty("timestamp_created")
        private Long timestampCreated;

        public Order() {
        }

        public String getPair() {
            return pair;
        }

        public void setPair(String pair) {
            this.pair = pair;
        }

        public OperationType getType() {
            return type;
        }

        public void setType(OperationType type) {
            this.type = type;
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

        public Long getTimestampCreated() {
            return timestampCreated;
        }

        public void setTimestampCreated(Long timestampCreated) {
            this.timestampCreated = timestampCreated;
        }
    }
}
