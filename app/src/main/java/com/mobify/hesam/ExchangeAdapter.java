package com.mobify.hesam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobify.model.Exchange;

import java.util.List;

/**
 * The class is responsible to
 * Created by hesam on 1/31/16.
 */
public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHolder>
{

    public static final String TAG = ExchangeAdapter.class.getSimpleName();

    private Context mContext;
    private List<Exchange> mExchangeList;

    public ExchangeAdapter(final Context context, final List<Exchange> exchangeList)
    {
        this.mContext = context;
        this.mExchangeList = exchangeList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_exchange_order, parent, false);

        return new ViewHolder(view);
    }

    public Exchange getItem(final int position)
    {
        return this.mExchangeList.get(position);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        // Get element from your dataSet at this position
        Exchange exchange = this.getItem(position);
        boolean needHeader = true;

        if (position > 0)
        {
            Exchange preExchange = this.getItem(--position);
            needHeader = exchange.getOrigin() != preExchange.getOrigin();
        }

        // Display header of Order is needed
        holder.tvHeader.setVisibility(needHeader ? View.VISIBLE : View.GONE);
        holder.tvHeader.setText(exchange.getOrigin() == Exchange.Origin.BUY?
                this.mContext.getString(R.string.buy) : this.mContext.getString(R.string.sell));

        // Display Order, for sake of memory optimization we use string builder
        StringBuilder builder = new StringBuilder();
        builder.append(this.addBlank(exchange.getQuantity()));
        builder.append(" ");
        builder.append(this.mContext.getString(R.string.moc_at_cad));
        builder.append(" ");
        builder.append(exchange.getPrice());
        holder.tvOrder.setText(builder.toString());
    }

    @Override
    public int getItemCount()
    {
        return mExchangeList == null? 0 : mExchangeList.size();
    }

    /**
     * Ah, ok, I know this is bull shit, but it works and developed in few seconds :)
     * Acts as beautifier if quantity's length is less than 5. For example if quantity is 123
     * then the function returns "  123"
     *
     * @param quantity quantity or order
     * @return few blanks in front of quantity
     */
    private String addBlank(int quantity)
    {
        String temp = String.valueOf(quantity);

        switch (temp.length())
        {
            case 4: return " " + temp;
            case 3: return "  " + temp;
            case 2: return "   " + temp;
            case 1: return "    " + temp;
            default: return temp;
        }
    }

    /**
     * Provide a reference to the views for each data item. View contains two elements, header and
     * order. Header must be visible for the first item of BUY or SELL list. It is visible by default.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvHeader;
        public TextView tvOrder;

        public ViewHolder(final View v)
        {
            super(v);

            tvHeader = (TextView) v.findViewById(R.id.tvHeader);
            tvOrder = (TextView) v.findViewById(R.id.tvOrder);
        }
    }
}
