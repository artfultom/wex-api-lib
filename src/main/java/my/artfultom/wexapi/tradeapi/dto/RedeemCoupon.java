package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedeemCoupon implements Serializable {

    @JsonProperty("success")
    private int success;

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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Transaction implements Serializable {

        @JsonProperty("couponAmount")
        private BigDecimal couponAmount;

        @JsonProperty("couponCurrency")
        private String couponCurrency;

        @JsonProperty("transID")
        private long transId;

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

        public long getTransId() {
            return transId;
        }

        public void setTransId(long transId) {
            this.transId = transId;
        }

        public Map<String, BigDecimal> getFunds() {
            return funds;
        }

        public void setFunds(Map<String, BigDecimal> funds) {
            this.funds = funds;
        }
    }
}
