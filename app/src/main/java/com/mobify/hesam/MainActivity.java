package com.mobify.hesam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("Abb");
        list.add("Acc");
        list.add("Baa");
        list.add("Bbb");
        list.add("Bcc");
        list.add("Bdd");
        list.add("Caa");
        list.add("Cbb");
        list.add("Ccc");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExchangeAdapter adapter = new ExchangeAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}
