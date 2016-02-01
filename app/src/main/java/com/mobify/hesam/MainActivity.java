package com.mobify.hesam;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mobify.model.Exchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private ExchangeAdapter mAdapter;

    private List<Exchange> mBuyList;
    private List<Exchange> mSellList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Exchange> list = getDemoExchangeList();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ExchangeAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
    }

    private List<Exchange> getDemoExchangeList()
    {
        this.mBuyList = getDemoBuyList();
        this.mSellList = getDemoSellList();

        // For sake of optimization I set it to 6 since I know the exact number
        List<Exchange> newList = new ArrayList<>(this.mBuyList);
        newList.addAll(this.mSellList);

        return newList;
    }

    private List<Exchange> getDemoBuyList()
    {
        List<Exchange> list = new ArrayList<>(3);

        Exchange exchange_1 = new Exchange();
        exchange_1.setCategory(Exchange.Category.BUY);
        exchange_1.setQuantity(100);
        exchange_1.setPrice(10.0f);
        exchange_1.setOrigin("SOMEONE");

        Exchange exchange_2 = new Exchange();
        exchange_2.setCategory(Exchange.Category.BUY);
        exchange_2.setQuantity(50);
        exchange_2.setPrice(9.50f);
        exchange_2.setOrigin("SOMEONE");

        Exchange exchange_3 = new Exchange();
        exchange_3.setCategory(Exchange.Category.BUY);
        exchange_3.setQuantity(20);
        exchange_3.setPrice(8.99f);
        exchange_3.setOrigin("SOMEONE");

        list.add(exchange_1);
        list.add(exchange_2);
        list.add(exchange_3);

        return list;
    }

    private List<Exchange> getDemoSellList()
    {
        List<Exchange> list = new ArrayList<>(3);

        Exchange exchange_1 = new Exchange();
        exchange_1.setCategory(Exchange.Category.SELL);
        exchange_1.setQuantity(10);
        exchange_1.setPrice(11.00f);
        exchange_1.setOrigin("SOMEONE");

        Exchange exchange_2 = new Exchange();
        exchange_2.setCategory(Exchange.Category.SELL);
        exchange_2.setQuantity(500);
        exchange_2.setPrice(12.50f);
        exchange_2.setOrigin("SOMEONE");

        Exchange exchange_3 = new Exchange();
        exchange_3.setCategory(Exchange.Category.SELL);
        exchange_3.setQuantity(200);
        exchange_3.setPrice(13.49f);
        exchange_3.setOrigin("SOMEONE");

        list.add(exchange_1);
        list.add(exchange_2);
        list.add(exchange_3);

        return list;
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnBuy:
                showOrderDialog(OrderDialogFragment.ORDER_CAT_BUYER);
                break;
            case R.id.btnSell:
                showOrderDialog(OrderDialogFragment.ORDER_CAT_SELLER);
                break;
            default:
                Log.e(TAG, "wtf, we shouldn't be there!!!");
                break;
        }
    }

    /**
     * Dialog is responsible to create and return {@link Exchange} object if user confirm it.
     *
     * @param orderCategory Pass OrderDialogFragment.ORDER_CAT_BUYER if User has clicked Buyer,
     *                      OrderDialogFragment.ORDER_CAT_SELLER if user has clicked Seller
     */
    private void showOrderDialog(int orderCategory)
    {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction. We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog
        DialogFragment newFragment = OrderDialogFragment.newInstance(orderCategory);
        newFragment.show(ft, "dialog");
    }

    /**
     * Gets called when user places an order. It gets called from {@link OrderDialogFragment}
     *
     * @param exchange
     */
    public void placeOrder(Exchange exchange)
    {
        Log.d(TAG, "=> " + exchange);

        // Case 1, User is selling and its price is higher than the max of Buy list
        if (exchange.getCategory() == Exchange.Category.SELL && exchange.getPrice() > this.mBuyList.get(0).getPrice())
        {
            this.mSellList.add(exchange);
            Collections.sort(this.mSellList, new ExchangeComparator());
            this.refreshRecyclerView();
            return;
        }

        // Case 2, User is buying and its price is less then the min of Sell list
        if (exchange.getCategory() == Exchange.Category.BUY && exchange.getPrice() < this.mSellList.get(0).getPrice())
        {
            this.mBuyList.add(exchange);
            Collections.sort(this.mBuyList, new ExchangeComparator());
            Collections.reverse(this.mBuyList);
            this.refreshRecyclerView();
            return;
        }

        // Case 3, User is buying and its price is in range of two orders in Sell list
        if (exchange.getCategory() == Exchange.Category.BUY)
        {
            Log.d(TAG, "*** I'm here ***");
            // Find candidate Exchange
            Exchange candidate = null;
            for (int i = 0; i <= this.mSellList.size(); i++)
            {
                if (exchange.getPrice() < this.mSellList.get(i).getPrice())
                {
                    candidate = this.mSellList.get(--i);
                    break;
                }

                candidate = this.mSellList.get(i);
            }

            // Candidate must be find, this is for later checking
            if (candidate == null)
            {
                Log.e(TAG, "Candidate did not found!");
                return;
            }

            // Case 3.1, If quantity of order is more than/equal to the quantity of candidate
            if (exchange.getQuantity() >= candidate.getQuantity())
            {
                exchange.buy(candidate.getOrigin(), candidate.getPrice(), candidate.getQuantity());
                this.mSellList.remove(candidate);
                this.mBuyList.add(exchange);
            }
            // Case 3.2, If quantity of order is less than the quantity of candidate
            else
            {
                exchange.buy(candidate.getOrigin(), candidate.getPrice(), exchange.getQuantity());
                candidate.setQuantity(candidate.getQuantity() - exchange.getQuantity());
            }

            Collections.sort(this.mSellList, new ExchangeComparator());
            Collections.sort(this.mBuyList, new ExchangeComparator());
            Collections.reverse(this.mBuyList);
            this.refreshRecyclerView();
            return;
        }
    }

    private void refreshRecyclerView()
    {
        // For sake of optimization I set it to 6 since I know the exact number
        List<Exchange> newList = new ArrayList<>(this.mBuyList);
        newList.addAll(this.mSellList);
        this.mAdapter.updateDataSet(newList);
    }
}
