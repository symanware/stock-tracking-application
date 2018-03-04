package com.snm.trade.stock.jackson;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class StockData {

    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_2018","root","root");
            con.setAutoCommit(false);
            String sql1="INSERT INTO stock_2018.stockinfo(symbol_id,symbol,last_refreshed)values(?,?,?)";
            PreparedStatement ps =con.prepareStatement(sql1);

            RestTemplate restTemplate = new RestTemplate();
            String dataStr = restTemplate.getForObject("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&outputsize=full&apikey=FOI5OFGH70BT5FTW", String.class);
            Stock stock = new Stock();
            try {
                JSONObject jsonObject = new JSONObject(dataStr);
                JSONObject jsonObject1 = jsonObject.getJSONObject("Meta Data");
                stock.setInformation(jsonObject1.getString("1. Information"));
                stock.setSymbol(jsonObject1.getString("2. Symbol"));
                ps.setInt(1,1);
                ps.setString(2,jsonObject1.getString("2. Symbol"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                java.util.Date convertedDate =  formatter.parse(jsonObject1.getString("3. Last Refreshed"));
                java.sql.Date date1 = new java.sql.Date(convertedDate.getTime());
               // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
              //  String date=jsonObject1.getString("3. Last Refreshed");
              //  Date date1= formatter.parse(date);
                ps.setDate(3, date1);
                ps.executeUpdate();
                con.commit();
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
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
catch(ParseException e){
                e.printStackTrace();
}



        }
        catch(SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void parseJSONdata(PreparedStatement ps) {

        RestTemplate restTemplate = new RestTemplate();
        String dataStr = restTemplate.getForObject("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&outputsize=full&apikey=FOI5OFGH70BT5FTW", String.class);
        Stock stock = new Stock();
        try {
            JSONObject jsonObject = new JSONObject(dataStr);
            JSONObject jsonObject1 = jsonObject.getJSONObject("Meta Data");
            stock.setInformation(jsonObject1.getString("1. Information"));
            stock.setSymbol(jsonObject1.getString("2. Symbol"));
            ps.setInt(1,1);
            ps.setString(2,jsonObject1.getString("2. Symbol"));
            ps.setString(3,jsonObject1.getString("3. Last Refreshed"));
           ps.executeUpdate();
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
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    private static void addDataIntoDB() {


    }


}
