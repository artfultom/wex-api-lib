package my.artfultom.wexapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class Trade implements Serializable {

    @JsonProperty("type")
    private String type;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("tid")
    private long tid;

    @JsonProperty("timestamp")
    private long timestamp;

    public Trade() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
