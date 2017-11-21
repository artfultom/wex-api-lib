package com.github.artfultom.wexapi.publicapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticker implements Serializable {

    @JsonProperty("low")
    private BigDecimal low;

    @JsonProperty("high")
    private BigDecimal high;

    @JsonProperty("avg")
    private BigDecimal avg;

    @JsonProperty("vol")
    private BigDecimal vol;

    @JsonProperty("vol_cur")
    private BigDecimal volCur;

    @JsonProperty("last")
    private BigDecimal last;

    @JsonProperty("buy")
    private BigDecimal buy;

    @JsonProperty("sell")
    private BigDecimal sell;

    @JsonProperty("updated")
    private long updated;

    public Ticker() {
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public BigDecimal getVolCur() {
        return volCur;
    }

    public void setVolCur(BigDecimal volCur) {
        this.volCur = volCur;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }
}
