package com.github.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawCoin implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("return")
    private Transaction transaction;

    public WithdrawCoin() {
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
         * Transaction ID.
         */
        @JsonProperty("tId")
        private long transactionId;

        /**
         * The amount sent including commission.
         */
        @JsonProperty("amountSent")
        private BigDecimal amountSent;

        /**
         * Balance after the request.
         */
        @JsonProperty("funds")
        private Map<String, BigDecimal> funds;

        public Transaction() {
        }

        public long getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(long transactionId) {
            this.transactionId = transactionId;
        }

        public BigDecimal getAmountSent() {
            return amountSent;
        }

        public void setAmountSent(BigDecimal amountSent) {
            this.amountSent = amountSent;
        }

        public Map<String, BigDecimal> getFunds() {
            return funds;
        }

        public void setFunds(Map<String, BigDecimal> funds) {
            this.funds = funds;
        }
    }
}
