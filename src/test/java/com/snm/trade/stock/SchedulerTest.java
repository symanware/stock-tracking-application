package com.snm.trade.stock;

import com.snm.trade.stock.service.alphavantage.RestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SchedulerTest {

    @Autowired
    private RestService restServiceMock;

    @Autowired
    Scheduler scheduler;

    @Test
    public void testSendAlert() throws Exception{
        when(restServiceMock.getStockData("RHFL.ns")).thenReturn("{\n" +
                "  \"Meta Data\": {\n" +
                "    \"1. Information\": \"Intraday (1min) prices and volumes\",\n" +
                "    \"2. Symbol\": \"RHFL\",\n" +
                "    \"3. Last Refreshed\": \"2018-03-02 16:00:00\",\n" +
                "    \"4. Interval\": \"1min\",\n" +
                "    \"5. Output Size\": \"Compact\",\n" +
                "    \"6. Time Zone\": \"US/Eastern\"\n" +
                "  },\n" +
                "  \"Time Series (1min)\": {\n" +
                "    \"2018-03-02 16:00:00\": {\n" +
                "      \"1. open\": 122.97,\n" +
                "      \"2. high\": 93.15,\n" +
                "      \"3. low\": 92.77,\n" +
                "      \"4. close\": 93.05,\n" +
                "      \"5. volume\": 4149916\n" +
                "    }\n" +
                "  }\n" +
                "}");
        scheduler.sendAlert();
    }
}
