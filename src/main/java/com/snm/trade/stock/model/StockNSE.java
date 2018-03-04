package com.snm.trade.stock.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockNSE {
    @JsonProperty("1. open")
    private BigDecimal openPrice;
    @JsonProperty("2. high")
    private BigDecimal highPrice;
    @JsonProperty("3. low")
    private BigDecimal lowPrice;
    @JsonProperty("4. close")
    private BigDecimal closePrice;
    @JsonProperty("5. volume")
    private Long volume;

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "NSE DATA for{Open Stock Price"+ openPrice +
                "highPrice=" + highPrice +
                "lowPrice" + lowPrice + " volume" + volume +
                '}';
    }

}
