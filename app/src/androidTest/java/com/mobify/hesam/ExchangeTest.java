package com.mobify.hesam;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mobify.model.Exchange;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExchangeTest
{
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    Exchange exchange;

    @Before
    public void setUp()
    {
        exchange = new Exchange();
    }

    @Test
    public void testBuy()
    {
        exchange.buy("Mobi", 1.0, 1);
    }

    @Test
    public void testSell()
    {
        exchange.sell("Fyi", 1.0, 1);
    }
}
