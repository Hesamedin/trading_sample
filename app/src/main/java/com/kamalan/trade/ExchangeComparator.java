package com.kamalan.trade;

import com.kamalan.model.Exchange;

import java.util.Comparator;

/**
 * The class to compare two {@link Exchange} object in order to sort list.
 *
 * Created by hesam on 1/31/16.
 */
public class ExchangeComparator implements Comparator<Exchange>
{
    @Override
    public int compare(Exchange o1, Exchange o2)
    {
        return o1.compareTo(o2);
    }
}
