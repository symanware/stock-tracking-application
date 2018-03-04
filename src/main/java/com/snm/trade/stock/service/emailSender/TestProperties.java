package com.snm.trade.stock.service.emailSender;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestProperties {

    @Value("${sym.test}")
    private String test;

    public String getTest(){
        return test;
    }
}
