package my.artfultom.wexapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Depth implements Serializable {

    @JsonProperty("asks")
    private List<BigDecimal[]> asks;

    @JsonProperty("bids")
    private List<BigDecimal[]> bids;

    public Depth() {
    }

    public List<BigDecimal[]> getAsks() {
        return asks;
    }

    public void setAsks(List<BigDecimal[]> asks) {
        this.asks = asks;
    }

    public List<BigDecimal[]> getBids() {
        return bids;
    }

    public void setBids(List<BigDecimal[]> bids) {
        this.bids = bids;
    }
}
