package com.snm.trade.stock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockDaily {

    @JsonProperty("Time Series (Daily)")
    private Date date;
    List<StockNSE> stockData= new ArrayList<StockNSE>();

    public List<StockNSE> getStockData() {
        return stockData;
    }

    public void setStockData(List<StockNSE> stockData) {
        this.stockData = stockData;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
