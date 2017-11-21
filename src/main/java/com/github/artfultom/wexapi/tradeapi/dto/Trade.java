package com.github.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trade implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("return")
    private ReturnValue returnValue;

    public Trade() {
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

    public ReturnValue getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReturnValue implements Serializable {

        /**
         * The amount of currency bought/sold.
         */
        @JsonProperty("received")
        private BigDecimal received;

        /**
         * The remaining amount of currency to be bought/sold (and the initial order amount).
         */
        @JsonProperty("remains")
        private BigDecimal remains;

        /**
         * Is equal to 0 if the request was fully “matched” by the opposite orders, otherwise the ID of the executed
         * order will be returned.
         */
        @JsonProperty("order_id")
        private long orderId;

        /**
         * Balance after the request.
         */
        @JsonProperty("funds")
        private Map<String, BigDecimal> funds;

        public ReturnValue() {
        }

        public BigDecimal getReceived() {
            return received;
        }

        public void setReceived(BigDecimal received) {
            this.received = received;
        }

        public BigDecimal getRemains() {
            return remains;
        }

        public void setRemains(BigDecimal remains) {
            this.remains = remains;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public Map<String, BigDecimal> getFunds() {
            return funds;
        }

        public void setFunds(Map<String, BigDecimal> funds) {
            this.funds = funds;
        }
    }
}
