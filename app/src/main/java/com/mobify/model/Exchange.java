package com.mobify.model;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Comparator;

/**
 * Implement the `Exchange` stub class found below. A stub test suite is also included.
 * <p/>
 * Feel free to extend or change the `Exchange` class methods or modify the test suite.
 * <p/>
 * Use whatever libraries you need to make it work.
 * <p/>
 * Good luck!
 */

public class Exchange implements Comparable
{
    public static final String TAG = Exchange.class.getSimpleName();

    /**
     * Type of order that sets the order in either of Buy/Sell category.
     */
    public enum Category
    {
        NONE,
        BUY,
        SELL
    }

    private Category mCategory;
    private String mOrigin;
    private float mPrice;
    private int mQuantity;

    public Exchange()
    {
        this.mCategory = Category.NONE;
    }

    public void buy(String origin, float price, int quantity)
    {
        this.mQuantity -= quantity;

        Log.d(TAG, "Number of " + quantity + " MOC bought at CA$" + price + " from " + origin);
    }

    public void sell(String origin, float price, int quantity)
    {
        this.mCategory = Category.SELL;
        this.mOrigin = origin;
        this.mPrice = price;
        this.mQuantity = quantity;

        Log.d(TAG, "Number of " + quantity + " MOC soled at CA$" + price + " to " + origin);
    }

    @Override
    public String toString()
    {
        return "Exchange {" +
                "mCategory=" + mCategory +
                ", mOrigin='" + mOrigin + '\'' +
                ", mPrice=" + mPrice +
                ", mQuantity=" + mQuantity +
                "}";
    }

    @Override
    public int compareTo(@NonNull Object another)
    {
        float anotherPrice = ((Exchange) another).getPrice();
        return Float.compare(this.mPrice, anotherPrice);
    }

    public Category getCategory()
    {
        return mCategory;
    }

    public void setCategory(Category category)
    {
        mCategory = category;
    }

    public int getQuantity()
    {
        return mQuantity;
    }

    public void setQuantity(int quantity)
    {
        mQuantity = quantity;
    }

    public float getPrice()
    {
        return mPrice;
    }

    public void setPrice(float price)
    {
        mPrice = price;
    }

    public String getOrigin()
    {
        return mOrigin;
    }

    public void setOrigin(String origin)
    {
        mOrigin = origin;
    }
}
