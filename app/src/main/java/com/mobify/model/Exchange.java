package com.mobify.model;

/**
 * Implement the `Exchange` stub class found below. A stub test suite is also included.
 * <p/>
 * Feel free to extend or change the `Exchange` class methods or modify the test suite.
 * <p/>
 * Use whatever libraries you need to make it work.
 * <p/>
 * Good luck!
 */

public class Exchange
{
    /**
     * Type of order that sets the order in either of Buy/Sell category.
     */
    public enum Origin
    {
        BUY,
        SELL
    }

    private Origin mOrigin;
    private float mPrice;
    private int mQuantity;

    public Exchange()
    {

    }

    public void buy(float price, int quantity)
    {
        this.mOrigin = Origin.BUY;
        this.mPrice = price;
        this.mQuantity = quantity;
    }

    public void sell(float price, int quantity)
    {
        this.mOrigin = Origin.SELL;
        this.mPrice = price;
        this.mQuantity = quantity;
    }

    public Origin getOrigin()
    {
        return mOrigin;
    }

    public void setOrigin(Origin origin)
    {
        mOrigin = origin;
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
}
