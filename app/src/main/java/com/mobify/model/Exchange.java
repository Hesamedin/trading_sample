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

    }

    public void buy(float price, int quantity)
    {
        this.mCategory = Category.BUY;
        this.mPrice = price;
        this.mQuantity = quantity;
    }

    public void sell(float price, int quantity)
    {
        this.mCategory = Category.SELL;
        this.mPrice = price;
        this.mQuantity = quantity;
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
