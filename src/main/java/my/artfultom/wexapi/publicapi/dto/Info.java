package my.artfultom.wexapi.publicapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {

    @JsonProperty("server_time")
    private long serverTime;

    @JsonProperty("pairs")
    private Map<String, Pair> pairs;

    public Info() {
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public Map<String, Pair> getPairs() {
        return pairs;
    }

    public void setPairs(Map<String, Pair> pairs) {
        this.pairs = pairs;
    }

    public static class Pair implements Serializable {

        @JsonProperty("decimal_places")
        private int decimalPlaces;

        @JsonProperty("min_price")
        private BigDecimal minPrice;

        @JsonProperty("max_price")
        private BigDecimal maxPrice;

        @JsonProperty("min_amount")
        private BigDecimal minAmount;

        @JsonProperty("hidden")
        private int hidden;

        @JsonProperty("fee")
        private BigDecimal fee;

        public Pair() {
        }

        public int getDecimalPlaces() {
            return decimalPlaces;
        }

        public void setDecimalPlaces(int decimalPlaces) {
            this.decimalPlaces = decimalPlaces;
        }

        public BigDecimal getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
        }

        public BigDecimal getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(BigDecimal maxPrice) {
            this.maxPrice = maxPrice;
        }

        public BigDecimal getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(BigDecimal minAmount) {
            this.minAmount = minAmount;
        }

        public int getHidden() {
            return hidden;
        }

        public void setHidden(int hidden) {
            this.hidden = hidden;
        }

        public BigDecimal getFee() {
            return fee;
        }

        public void setFee(BigDecimal fee) {
            this.fee = fee;
        }
    }
}
