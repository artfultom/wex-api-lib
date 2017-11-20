package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawCoin implements Serializable {

    @JsonProperty("success")
    private int success;

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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Transaction implements Serializable {

        @JsonProperty("tId")
        private long tId;

        @JsonProperty("amountSent")
        private BigDecimal amountSent;

        @JsonProperty("funds")
        private Map<String, BigDecimal> funds;

        public Transaction() {
        }

        public long gettId() {
            return tId;
        }

        public void settId(long tId) {
            this.tId = tId;
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
