package com.snm.trade.stock.controller;

import com.snm.trade.stock.model.Stock;
import com.snm.trade.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @GetMapping("/stocks")
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> getNoteById(@PathVariable(value = "id") String stockId) {
        Stock stock = stockRepository.findStockByStockId(stockId);
        if(stock == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(stock);
    }

    @PostMapping("/stocks")
    public Stock createStock(@Valid @RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<Stock> updateNote(@PathVariable(value = "id") String stockId,
                                            @Valid @RequestBody Stock stockDetails) {
        Stock stock = stockRepository.findStockByStockId(stockId);
        if(stock == null) {
            return ResponseEntity.notFound().build();
        }
        stock.setStockId(stockDetails.getStockId());
        stock.setBuyPrice(stockDetails.getBuyPrice());
        stock.setStoplossPrice(stockDetails.getStoplossPrice());
        stock.setTargetPrice(stockDetails.getTargetPrice());
        Stock updateStock = stockRepository.save(stock);
        return ResponseEntity.ok(updateStock);
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Stock> deleteNote(@PathVariable(value = "id") String stockId) {
        Stock stock = stockRepository.findStockByStockId(stockId);
        if(stock == null) {
            return ResponseEntity.notFound().build();
        }
        stockRepository.delete(stock);
        return ResponseEntity.ok().build();
    }
}
