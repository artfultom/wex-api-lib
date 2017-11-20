package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import my.artfultom.wexapi.util.OperationType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeHistory implements Serializable {

    @JsonProperty("success")
    private int success;

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

    public Map<String, Trade> getTrades() {
        return trades;
    }

    public void setTrades(Map<String, Trade> trades) {
        this.trades = trades;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Trade implements Serializable {

        @JsonProperty("pair")
        private String pair;

        @JsonProperty("type")
        private OperationType type;

        @JsonProperty("amount")
        private BigDecimal amount;

        @JsonProperty("rate")
        private BigDecimal rate;

        @JsonProperty("order_id")
        private long orderId;

        @JsonProperty("is_your_order")
        private int isYourOrder;

        @JsonProperty("timestamp")
        private Long timestamp;

        public Trade() {
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

        public int getIsYourOrder() {
            return isYourOrder;
        }

        public void setIsYourOrder(int isYourOrder) {
            this.isYourOrder = isYourOrder;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }
    }
}
