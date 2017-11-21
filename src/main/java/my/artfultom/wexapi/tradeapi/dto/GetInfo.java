package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import my.artfultom.wexapi.util.DateDeserializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInfo implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("return")
    private ReturnValue returnValue;

    public GetInfo() {
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
         * Your account balance available for trading. Doesn’t include funds on your open orders.
         */
        @JsonProperty("funds")
        private Map<String, BigDecimal> funds;

        /**
         * The privileges of the current API key. At this time the privilege to withdraw is not used anywhere.
         */
        @JsonProperty("rights")
        private Map<String, Integer> rights;

        /**
         * The number of your open orders.
         */
        @JsonProperty("open_orders")
        private int openOrders;

        /**
         * Server time (MSK).
         */
        @JsonProperty("server_time")
        @JsonDeserialize(using = DateDeserializer.class)
        private LocalDateTime serverTime;

        public ReturnValue() {
        }

        public Map<String, BigDecimal> getFunds() {
            return funds;
        }

        public void setFunds(Map<String, BigDecimal> funds) {
            this.funds = funds;
        }

        public Map<String, Integer> getRights() {
            return rights;
        }

        public void setRights(Map<String, Integer> rights) {
            this.rights = rights;
        }

        public int getOpenOrders() {
            return openOrders;
        }

        public void setOpenOrders(int openOrders) {
            this.openOrders = openOrders;
        }

        public LocalDateTime getServerTime() {
            return serverTime;
        }

        public void setServerTime(LocalDateTime serverTime) {
            this.serverTime = serverTime;
        }
    }
}
