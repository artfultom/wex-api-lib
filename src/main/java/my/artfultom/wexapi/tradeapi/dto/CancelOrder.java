package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrder implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("return")
    private ReturnValue returnValue;

    public CancelOrder() {
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
         * The ID of canceled order.
         */
        @JsonProperty("order_id")
        private long orderId;

        /**
         * Balance upon request.
         */
        @JsonProperty("funds")
        private Map<String, BigDecimal> funds;

        public ReturnValue() {
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
