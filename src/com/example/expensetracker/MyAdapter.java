package com.example.expensetracker;

import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

// -------------------------------------------------------------------------
/**
 * Creates customized adapter for ListView.
 *
 * @author Adam Lech
 * @version Jun 17, 2013
 */
public class MyAdapter
    extends BaseAdapter
{
    /**
     * List of records.
     */
    ArrayList<Transaction> listTransaction;
    private LayoutInflater layoutInflater;


    // ----------------------------------------------------------
    /**
     * Constructor
     *
     * @param context
     * @param listRecord
     */
    public MyAdapter(Context context, ArrayList<Transaction> listRecord)
    {
        this.listTransaction = listRecord;
        layoutInflater = LayoutInflater.from(context);
    }


    public int getCount()
    {

        return listTransaction.size();
    }


    public Object getItem(int position)
    {
        return listTransaction.get(position);
    }


    public long getItemId(int position)
    {

        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        View someView = null;

        if (convertView == null)
        {
            someView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.dateView = (TextView)someView.findViewById(R.id.date);
            holder.placeView = (TextView)someView.findViewById(R.id.place);
            holder.transactionView =
                (TextView)someView.findViewById(R.id.deposit);
            holder.balanceView =
                (TextView)someView.findViewById(R.id.balance);
            someView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();

        }
        holder.dateView.setText(listTransaction.get(position).getCurrentDate());
        holder.transactionView.setText(listTransaction.get(position)
            .getWithdrawal());
        holder.placeView.setText(listTransaction.get(position).getPlace());
        try
        {

            holder.balanceView.setText(listTransaction.get(position)
                .getBalance());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return convertView != null ? convertView : someView;

    }


    // -------------------------------------------------------------------------
    /**
     * Class to contain different data fields that belongs to record.
     *
     * @author Adam Lech
     * @version Jun 17, 2013
     */
    static class ViewHolder
    {
        /**
         * Date of transaction
         */
        TextView dateView;
        /**
         * Place of transaction.
         */
        TextView placeView;
        /**
         * Deposit text view.
         */
        TextView transactionView;
        /**
         * Withdrawal text view.
         */
        TextView balanceView;

    }
}
