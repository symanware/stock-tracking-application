package com.snm.trade.stock.service.alphavantage;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    public String getStockData(String stockId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + stockId + "&interval=1min&outputsize=full&apikey=FOI5OFGH70BT5FTW", String.class);
    }
}
