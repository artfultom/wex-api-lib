package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCoupon implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("return")
    private Transaction transaction;

    public CreateCoupon() {
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

        /**
         * Generated coupon.
         */
        @JsonProperty("coupon")
        private String coupon;

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

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
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
