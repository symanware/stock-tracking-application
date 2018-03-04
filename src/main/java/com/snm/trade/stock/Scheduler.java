package com.snm.trade.stock;

import com.snm.trade.stock.model.Stock;
import com.snm.trade.stock.service.EmailServiceImpl;
import com.snm.trade.stock.service.StockService;
import com.snm.trade.stock.service.alphavantage.RestService;
import com.snm.trade.stock.service.emailSender.TestProperties;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private TestProperties testProperties;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private StockService stockService;

    @Autowired
    private RestService restService;

    //TODO: will run every 30 minutes
    // Get all stock from database
    // this will call alphavantage to know current share price
    // current share price will compared with the values in the database buy price value
    // fetch pecentage increase, if the share price is increased as define, send a mail

    @Scheduled(fixedDelay = 30000)
    public void sendAlert() throws Exception {
        List<Stock> stockList = stockService.getAllStocks();
        float difference;
        for (Stock stock : stockList) {
            String comp = stock.getStockId();
            String company = comp.concat(".ns");
            String dataStr = restService.getStockData(company);
            if (org.springframework.util.StringUtils.isEmpty(dataStr)) {
                System.out.println("Data not available to test");
            }
            try {
                JSONObject jsonObject = new JSONObject(dataStr);
                JSONObject jsonObject1 = jsonObject.getJSONObject("Meta Data");
                if (comp.equals(jsonObject1.getString("2. Symbol"))) {

                    JSONObject jsonObject2 = jsonObject.getJSONObject("Time Series (1min)");
                    Iterator keysIterator = jsonObject2.keys();
                    String key = (String) keysIterator.next();
                    JSONObject jsonObject3 = jsonObject2.getJSONObject(key);
                    String currentStockValue = jsonObject3.getString("1. open");
                    System.out.println("Current Stock value from website:" + currentStockValue);
                    System.out.println("Stock value from DB:" + stock.getTargetPrice());
                    Float actualValue = Float.valueOf(currentStockValue);
                    //  float difference = (stock.getBuyPrice().floatValue()) - actualValue;
                    if (actualValue > stock.getBuyPrice().floatValue()) {
                        difference = actualValue - (stock.getBuyPrice().floatValue());
                    } else {
                        difference = (stock.getBuyPrice().floatValue()) - actualValue;
                    }
                   // Float diff = Math.round(difference);

                    Integer value = stock.getBuyPrice().intValue();
                    Float stockCurrentvalue=((difference / value) * 100);
                      if ((Math.round(stockCurrentvalue)) > (Math.round(stock.getTargetPrice()))) {
                  //  if (diff > stock.getTargetPrice()) {
                        String to = "shilpa.manware17@gmail.com";
                        String subject = "Important Stock Update";
                        String text = "You need to check this urgently";
                        emailService.sendSimpleMessage(to, subject, text);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
