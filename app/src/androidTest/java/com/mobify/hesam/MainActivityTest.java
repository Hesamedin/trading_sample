package com.mobify.hesam;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mobify.model.Exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest
{
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mMainActivity;

    @Before
    public void setUp()
    {
        mMainActivity = mActivityRule.getActivity();
    }

    @Test
    public void checkRecyclerViewIsVisible()
    {
        onView(withId(R.id.recyclerView)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void checkRecyclerViewContainsDemoLists()
    {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
    }

    // Case 1, User is selling and its price is higher than the max of Buy list
    @Test
    public void checkTestCase_1()
    {
        final Exchange exchange = new Exchange();
        exchange.setCategory(Exchange.Category.SELL);
        exchange.setOrigin("Me");
        exchange.setPrice(12.0f); // Max price of item in BuyList is 10.0
        exchange.setQuantity(20);

        mMainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mMainActivity.placeOrder(exchange);

                // Three demo items are there already
                Assert.assertEquals(4, mMainActivity.getSellList().size());
            }
        });
    }

    // Case 2, User is buying and its price is less then the min of Sell list
    @Test
    public void checkTestCase_2()
    {
        final Exchange exchange = new Exchange();
        exchange.setCategory(Exchange.Category.BUY);
        exchange.setOrigin("Me");
        exchange.setPrice(8.0f); // Min price of item in SellList is 11.0
        exchange.setQuantity(20);

        mMainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mMainActivity.placeOrder(exchange);

                // Three demo items are there already
                Assert.assertEquals(4, mMainActivity.getBuyList().size());
            }
        });
    }

    // Case 3, User is buying and his price is more the min price in Sell list
    // Case 3.1, If quantity of order is more than the quantity of candidate
    @Test
    public void checkTestCase_3_1()
    {
        final Exchange exchange = new Exchange();
        exchange.setCategory(Exchange.Category.BUY);
        exchange.setOrigin("Me");
        exchange.setPrice(13.0f); // Min price of item in SellList is 11.0
        exchange.setQuantity(600);

        mMainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mMainActivity.placeOrder(exchange);

                // 2 out of 3 items must be removed
                Assert.assertEquals(1, mMainActivity.getSellList().size());

                // 1 item must be added to the BuyList
                Assert.assertEquals(4, mMainActivity.getBuyList().size());
            }
        });
    }

    // Case 3, User is buying and his price is more the min price in Sell list
    // Case 3.2, If quantity of order is equal to the quantity of candidate
    @Test
    public void checkTestCase_3_2()
    {
        final Exchange exchange = new Exchange();
        exchange.setCategory(Exchange.Category.BUY);
        exchange.setOrigin("Me");
        exchange.setPrice(13.0f); // Min price of item in SellList is 11.0
        exchange.setQuantity(500);

        mMainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mMainActivity.placeOrder(exchange);

                // 1 item must be removed
                Assert.assertEquals(2, mMainActivity.getSellList().size());

                // BuyList shouldn't get effect
                Assert.assertEquals(3, mMainActivity.getBuyList().size());
            }
        });
    }

    // Case 3, User is buying and his price is more the min price in Sell list
    // Case 3.3, If quantity of order is less than the quantity of candidate
    @Test
    public void checkTestCase_3_3()
    {
        final Exchange exchange = new Exchange();
        exchange.setCategory(Exchange.Category.BUY);
        exchange.setOrigin("Me");
        exchange.setPrice(13.0f); // Min price of item in SellList is 11.0
        exchange.setQuantity(300);

        mMainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mMainActivity.placeOrder(exchange);

                // No item must be removed
                Assert.assertEquals(3, mMainActivity.getSellList().size());

                // No change should be made in BuyList
                Assert.assertEquals(3, mMainActivity.getBuyList().size());
            }
        });
    }
}
