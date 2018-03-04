package com.snm.trade.stock;
        import com.snm.trade.stock.model.StockDaily;
        import com.snm.trade.stock.model.StockNSE;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.web.client.RestTemplate;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        StockDaily data = restTemplate.getForObject("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=RHFL.NS&apikey=FOI5OFGH70BT5FTW", StockDaily.class);
        log.info(String.valueOf(data.getStockData()).toString());
    }

}

