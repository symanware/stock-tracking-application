package com.snm.trade.stock;

import com.snm.trade.stock.service.alphavantage.RestService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class RestServiceTestConfiguration {
    @Bean
    @Primary
    public RestService restService() {
        return Mockito.mock(RestService.class);
    }
}
