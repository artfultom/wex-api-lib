package com.github.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.artfultom.wexapi.util.DateDeserializer;
import com.github.artfultom.wexapi.util.TransactionStatus;
import com.github.artfultom.wexapi.util.TransactionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionsHistory implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("return")
    private Map<String, Transaction> transactions;

    public TransactionsHistory() {
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

    public Map<String, Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<String, Transaction> transactions) {
        this.transactions = transactions;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Transaction implements Serializable {

        /**
         * Transaction type. 1/2 - deposit/withdrawal, 4/5 - credit/debit.
         */
        @JsonProperty("type")
        private TransactionType transactionType;

        /**
         * Transaction amount.
         */
        @JsonProperty("amount")
        private BigDecimal amount;

        /**
         * Transaction currency.
         */
        @JsonProperty("currency")
        private String currency;

        /**
         * Transaction description.
         */
        @JsonProperty("desc")
        private String description;

        /**
         * Transaction status. 0 - canceled/failed, 1 - waiting for acceptance, 2 - successful, 3 – not confirmed
         */
        @JsonProperty("status")
        private TransactionStatus status;

        /**
         * Transaction time.
         */
        @JsonProperty("timestamp")
        @JsonDeserialize(using = DateDeserializer.class)
        private LocalDateTime timestamp;

        public Transaction() {
        }

        public TransactionType getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public TransactionStatus getStatus() {
            return status;
        }

        public void setStatus(TransactionStatus status) {
            this.status = status;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
}
