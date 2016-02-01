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
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;

    private List<Exchange> buyList;
    private List<Exchange> sellList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Exchange> list = getDemoExchangeList();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExchangeAdapter adapter = new ExchangeAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    private List<Exchange> getDemoExchangeList()
    {
        this.buyList = getDemoBuyList();
        this.sellList = getDemoSellList();

        // For sake of optimization I set it to 6 since I know the exact number
        List<Exchange> newList = new ArrayList<>(this.buyList);
        newList.addAll(this.sellList);

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

    public void placeOrder(Exchange exchange)
    {
        Log.d(TAG, "=> " + exchange);
    }
}
