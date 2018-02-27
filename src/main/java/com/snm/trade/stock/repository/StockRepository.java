package com.snm.trade.stock.repository;

import com.snm.trade.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findStockByStockId(String stockId);
}
