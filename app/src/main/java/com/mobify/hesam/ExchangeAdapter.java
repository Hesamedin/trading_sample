package com.mobify.hesam;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * The class is responsible to
 * Created by hesam on 1/31/16.
 */
public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHolder>
{

    public static final String TAG = ExchangeAdapter.class.getSimpleName();

    private Context mContext;
    private List<String> mCountries;

    public ExchangeAdapter(Context context, List<String> countries)
    {
        this.mContext = context;
        this.mCountries = countries;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_exchange_order, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mCountries.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mCountries == null? 0 : mCountries.size();
    }

    /**
     * Provide a reference to the views for each data item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(final View v)
        {
            super(v);

            mTextView = (TextView) v.findViewById(R.id.tvCountry);
        }
    }
}
