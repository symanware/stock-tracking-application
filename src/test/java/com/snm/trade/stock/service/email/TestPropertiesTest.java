package com.snm.trade.stock.service.email;


import com.snm.trade.stock.service.emailSender.TestProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPropertiesTest {

    @Autowired
    private TestProperties testProperties;

    @Test
    public void testGetter() {
        Assert.assertNotNull(testProperties.getTest());
    }
}
