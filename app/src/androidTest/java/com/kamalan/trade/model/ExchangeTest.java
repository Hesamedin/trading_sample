package com.kamalan.trade.model;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kamalan.trade.MainActivity;
import com.kamalan.model.Exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExchangeTest
{
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private Exchange exchange;

    @Before
    public void setUp()
    {
        exchange = new Exchange();
        exchange.setCategory(Exchange.Category.NONE);
        exchange.setOrigin("Me");
        exchange.setQuantity(20);
        exchange.setPrice(12.5f);
    }

    @Test
    public void testBuy()
    {
        exchange.setCategory(Exchange.Category.BUY);
        exchange.buy("Mobi", 1.0f, 1);

        Assert.assertEquals(19, exchange.getQuantity());
    }

    @Test
    public void testSell()
    {
        exchange.setCategory(Exchange.Category.SELL);
        exchange.sell("Fyi", 1.0f, 5);

        Assert.assertEquals(15, exchange.getQuantity());
    }
}
