package com.snm.trade.stock.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snm.trade.stock.jackson.TimeSeries;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "stocksrest")
@EntityListeners(AuditingEntityListener.class)

public class StockRest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(nullable = false)
    public String symbol;
    @Column(nullable = false)
    public String information;
    @Column(nullable = false)

  //  public List<TimeSeries> timeSeries = new ArrayList<>();

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

  /*  public List<TimeSeries> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(List<TimeSeries> timeSeries) {
        this.timeSeries = timeSeries;
    }*/
}
