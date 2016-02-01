package com.mobify.hesam;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobify.model.Exchange;

/**
 * The order dialog is responsible to get order from user. Order category, Buy or Sell, should be
 * sent during object creation and it will returned once user placed the order.
 * <p/>
 * Created by hesam on 1/31/16.
 */
public class OrderDialogFragment extends DialogFragment implements View.OnClickListener
{
    public static final String TAG = OrderDialogFragment.class.getSimpleName();

    private static final String KEY_CATEGORY = "order_category";
    public static final int ORDER_CAT_BUYER = 1;
    public static final int ORDER_CAT_SELLER = 2;

    private Exchange mExchange;

    private EditText mEtQuantity;
    private EditText mEtPrice;

    /**
     * Create a new instance of MyDialogFragment, providing "category" as an argument.
     */
    static OrderDialogFragment newInstance(int category)
    {
        OrderDialogFragment f = new OrderDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt(OrderDialogFragment.KEY_CATEGORY, category);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.dialog_order_fragment, container, false);

        TextView tvBuySell = (TextView) v.findViewById(R.id.tvBuySell);
        mEtQuantity = (EditText) v.findViewById(R.id.etQuantity);
        mEtPrice = (EditText) v.findViewById(R.id.etPrice);

        Button btnConfirm = (Button) v.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);
        Button btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        // create new object
        mExchange = new Exchange();
        mExchange.setOrigin("ME");

        int category = getArguments().getInt(OrderDialogFragment.KEY_CATEGORY);
        if (category == OrderDialogFragment.ORDER_CAT_BUYER)
        {
            getDialog().setTitle("I am Buyer");
            tvBuySell.setText(getResources().getString(R.string.buy));
            mExchange.setCategory(Exchange.Category.BUY);
        }
        else if (category == OrderDialogFragment.ORDER_CAT_SELLER)
        {
            tvBuySell.setText(getResources().getString(R.string.sell));
            getDialog().setTitle("I am Seller");
            mExchange.setCategory(Exchange.Category.SELL);
        }
        else
        {
            mExchange.setCategory(Exchange.Category.NONE);
            Log.e(TAG, "Wrong Order Category has assigned to this dialog :(");
            this.getDialog().dismiss();
        }

        return v;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCancel:
                break;
            case R.id.btnConfirm:
                int quantity = 0;
                float price = 0.00f;

                try
                {
                    quantity = Integer.valueOf(this.mEtQuantity.getText().toString().trim());
                    price = Float.valueOf(this.mEtPrice.getText().toString().trim());
                }
                catch (NumberFormatException e)
                {
                    // is not that critical since we are handle it
                }

                if (quantity > 0 && price > 0)
                {
                    this.mExchange.setQuantity(quantity);
                    this.mExchange.setPrice(price);
                    ((MainActivity) getActivity()).placeOrder(this.mExchange);
                }
                else
                {
                    Toast.makeText(getActivity(), "Both of Quantity and Price are reqyired.", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            default:
                Log.e(TAG, "omg, how come you are there!!!");
                break;
        }

        // Close the dialog
        this.getDialog().dismiss();
    }
}
