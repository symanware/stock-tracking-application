package com.snm.trade.stock.service;

import com.snm.trade.stock.model.Stock;
import com.snm.trade.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService
{

    @Autowired
    StockRepository stockRepository;
    @Autowired
    EmailServiceImpl emailService;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockById(String stockId) {
        return stockRepository.findStockByStockId(stockId);
    }
}
