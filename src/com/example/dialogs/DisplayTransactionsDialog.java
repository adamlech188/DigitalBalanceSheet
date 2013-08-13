package com.example.dialogs;

import android.view.View.OnTouchListener;
import android.widget.Button;
import android.view.View;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import com.example.expensetracker.R;
import android.os.Bundle;
import android.content.Context;
import android.app.Dialog;

// -------------------------------------------------------------------------
/**
 * Dialog that displays different options for showing different numbers of
 * transactions.
 *
 * @author Adam Lech
 * @version Aug 12, 2013
 */
public class DisplayTransactionsDialog
    extends Dialog
{
    private Button allTransactionsButton;
    private Button last15TransactionsButton;
    private Button last30TransactionsButton;
    private Button last60TransactionsButton;


    // ----------------------------------------------------------
    /**
     * Create a new DisplayTransactionsDialog object.
     *
     * @param context
     */
    public DisplayTransactionsDialog(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.display_transactions);
        allTransactionsButton = (Button)findViewById(R.id.all_transactions);
        last15TransactionsButton = (Button)findViewById(R.id.last_15);
        last30TransactionsButton = (Button)findViewById(R.id.last_30);
        last60TransactionsButton = (Button)findViewById(R.id.last_60);
    }


    // ----------------------------------------------------------
    /**
     * Sets on touch listener.
     *
     * @param onTouchListener
     */
    public void setOnTouchListener(OnTouchListener onTouchListener)
    {
        allTransactionsButton.setOnTouchListener(onTouchListener);
        last15TransactionsButton.setOnTouchListener(onTouchListener);
        last30TransactionsButton.setOnTouchListener(onTouchListener);
        last60TransactionsButton.setOnTouchListener(onTouchListener);
    }

}
