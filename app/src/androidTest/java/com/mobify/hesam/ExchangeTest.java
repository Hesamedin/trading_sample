package com.mobify.hesam;

import android.test.AndroidTestCase;

import com.mobify.model.Exchange;

public class ExchangeTest extends AndroidTestCase {
    Exchange exchange;

    @Override
    public void setUp() {
        exchange = new Exchange();
    }

    public void testBuy() {
        exchange.buy("Mobi", 1.0, 1);
    }

    public void testSell() {
        exchange.sell("Fyi", 1.0, 1);
    }
}
