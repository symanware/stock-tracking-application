package com.snm.trade.stock.jackson;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;

public class StockDeserializerTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String dataStr = restTemplate.getForObject("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&outputsize=full&apikey=FOI5OFGH70BT5FTW", String.class);
        Stock stock = new Stock();
        try {
            JSONObject jsonObject = new JSONObject(dataStr);
            JSONObject jsonObject1 = jsonObject.getJSONObject("Meta Data");
            stock.setInformation(jsonObject1.getString("1. Information"));
            stock.setSymbol(jsonObject1.getString("2. Symbol"));
            JSONObject jsonObject2 = jsonObject.getJSONObject("Time Series (1min)");
            Iterator keysIterator = jsonObject2.keys();
            while (keysIterator.hasNext()) {
                String key = (String) keysIterator.next();
                JSONObject jsonObject3 = jsonObject2.getJSONObject(key);
                TimeSeries timeSeries = new TimeSeries();
                timeSeries.setOpen(jsonObject3.getString("1. open"));
                timeSeries.setHigh(jsonObject3.getString("2. high"));
                timeSeries.setLow(jsonObject3.getString("3. low"));
                timeSeries.setClose(jsonObject3.getString("4. close"));
                timeSeries.setVolume(jsonObject3.getString("5. volume"));
                stock.getTimeSeries().add(timeSeries);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("TimeSeries size: " + stock.getTimeSeries().size());
    }
}
