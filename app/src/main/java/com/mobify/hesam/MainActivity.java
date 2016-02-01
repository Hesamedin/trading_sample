package com.mobify.hesam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

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
        exchange_1.setOrigin(Exchange.Origin.BUY);
        exchange_1.setQuantity(100);
        exchange_1.setPrice(10.0f);

        Exchange exchange_2 = new Exchange();
        exchange_2.setOrigin(Exchange.Origin.BUY);
        exchange_2.setQuantity(50);
        exchange_2.setPrice(9.50f);

        Exchange exchange_3 = new Exchange();
        exchange_3.setOrigin(Exchange.Origin.BUY);
        exchange_3.setQuantity(20);
        exchange_3.setPrice(8.99f);

        list.add(exchange_1);
        list.add(exchange_2);
        list.add(exchange_3);

        return list;
    }

    private List<Exchange> getDemoSellList()
    {
        List<Exchange> list = new ArrayList<>(3);

        Exchange exchange_1 = new Exchange();
        exchange_1.setOrigin(Exchange.Origin.SELL);
        exchange_1.setQuantity(10);
        exchange_1.setPrice(11.00f);

        Exchange exchange_2 = new Exchange();
        exchange_2.setOrigin(Exchange.Origin.SELL);
        exchange_2.setQuantity(500);
        exchange_2.setPrice(12.50f);

        Exchange exchange_3 = new Exchange();
        exchange_3.setOrigin(Exchange.Origin.SELL);
        exchange_3.setQuantity(200);
        exchange_3.setPrice(13.49f);

        list.add(exchange_1);
        list.add(exchange_2);
        list.add(exchange_3);

        return list;
    }
}
