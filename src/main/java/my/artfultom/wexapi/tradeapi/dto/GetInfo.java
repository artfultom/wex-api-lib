package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInfo implements Serializable {

    @JsonProperty("success")
    private int success;

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

    public ReturnValue getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReturnValue implements Serializable {

        @JsonProperty("funds")
        private Map<String, BigDecimal> funds;

        @JsonProperty("rights")
        private Map<String, Integer> rights;

        @JsonProperty("open_orders")
        private int openOrders;

        @JsonProperty("server_time")
        private long serverTime;

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

        public long getServerTime() {
            return serverTime;
        }

        public void setServerTime(long serverTime) {
            this.serverTime = serverTime;
        }
    }
}
