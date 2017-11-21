package com.github.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedeemCoupon implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("return")
    private Transaction transaction;

    public RedeemCoupon() {
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Transaction implements Serializable {

        /**
         * The amount that has been redeemed.
         */
        @JsonProperty("couponAmount")
        private BigDecimal couponAmount;

        /**
         * The currency of the coupon that has been redeemed.
         */
        @JsonProperty("couponCurrency")
        private String couponCurrency;

        /**
         * Transaction ID.
         */
        @JsonProperty("transID")
        private long transactionId;

        /**
         * Balance after the request.
         */
        @JsonProperty("funds")
        private Map<String, BigDecimal> funds;

        public Transaction() {
        }

        public BigDecimal getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(BigDecimal couponAmount) {
            this.couponAmount = couponAmount;
        }

        public String getCouponCurrency() {
            return couponCurrency;
        }

        public void setCouponCurrency(String couponCurrency) {
            this.couponCurrency = couponCurrency;
        }

        public long getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(long transactionId) {
            this.transactionId = transactionId;
        }

        public Map<String, BigDecimal> getFunds() {
            return funds;
        }

        public void setFunds(Map<String, BigDecimal> funds) {
            this.funds = funds;
        }
    }
}
