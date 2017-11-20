package my.artfultom.wexapi.tradeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinDepositAddress implements Serializable {

    @JsonProperty("success")
    private int success;

    @JsonProperty("return")
    private DepositAddress depositAddress;

    public CoinDepositAddress() {
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public DepositAddress getDepositAddress() {
        return depositAddress;
    }

    public void setDepositAddress(DepositAddress depositAddress) {
        this.depositAddress = depositAddress;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DepositAddress implements Serializable {

        @JsonProperty("address")
        private String address;

        public DepositAddress() {
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
