package com.example.expensetracker;

import com.example.dialogs.NewBalanceSheetDialog;
import com.example.dialogs.MyAlertDialog;
import android.view.MenuItem;
import com.example.expensetracker.R;
import android.widget.Button;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import com.example.dialogs.AddDialog;
import com.example.datatable.TransactionsDataSource;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * Creates main activity of expense tracker.
 *
 * @author Adam Lech
 * @version Jun 16, 2013
 */
public class ExpenseTrackerActivity
    extends Activity
{

    private ListView               monthsListView;
    private AddDialog              addDialogBox;
    private Float                  CURRENT_BALANCE = -12.98f;
    private MyAlertDialog          myAlertDialog;

    /**
     * Record array
     */
    List<Transaction>              transactionArray;
    private MyAdapter              myAdapter;
    private TransactionsDataSource dataSource;
    private boolean                isWithdrawal    = true;
    private NewBalanceSheetDialog  newBalanceSheetDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSource = new TransactionsDataSource(this);

        // Dialog box widgets.
        addDialogBox = new AddDialog(this);
        myAlertDialog = new MyAlertDialog(this);
        newBalanceSheetDialog = new NewBalanceSheetDialog(this);

        dataSource.open();

        monthsListView = (ListView)findViewById(R.id.months_list);
        transactionArray = dataSource.getAllTransaction();
        if (!transactionArray.isEmpty())
        {
            try
            {
                CURRENT_BALANCE =
                    Float.parseFloat(transactionArray.get(0).getBalance()
                        .substring(1));
            }
            catch (NumberFormatException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        myAdapter = new MyAdapter(this, (ArrayList)transactionArray);
        monthsListView.setAdapter(myAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Selects option from the menu.
     *
     * @param item
     * @return true or false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.new_balance)
        {
            myAlertDialog.show();
            DialogListener dialogListener = new DialogListener();
            myAlertDialog.getYesButton().setOnTouchListener(dialogListener);
            myAlertDialog.getNoButton().setOnTouchListener(dialogListener);

        }
        return true;

    }


    // ----------------------------------------------------------
    /**
     * Adds record to list view.
     *
     * @param view
     */
    public void withdrawalButtonClicked(View view)
    {
        isWithdrawal = true;
        addDialogBox.show();
        addDialogBox.setTitle("WITHDRAWAL");
        Button addTransactionButton = addDialogBox.getaddTransactionButton();
        OnTouchListener dialogListener = new DialogListener();
        addTransactionButton.setOnTouchListener(dialogListener);

    }


    // ----------------------------------------------------------
    /**
     * Adds deposit transaction.
     *
     * @param view
     */
    public void depositButtonClicked(View view)
    {
        isWithdrawal = false;
        addDialogBox.show();
        addDialogBox.setTitle("DEPOSIT");
        Button addTransactionButton = addDialogBox.getaddTransactionButton();
        OnTouchListener dialogListener = new DialogListener();
        addTransactionButton.setOnTouchListener(dialogListener);

    }


    private void addRecord(char sign)
    {
        EditText placeDialog = addDialogBox.getplaceField();
        EditText amountDialog = addDialogBox.getamountField();
        CharSequence somePlace = placeDialog.getText();
        CharSequence somePrice = amountDialog.getText();
        ;
        Balance someBalance = new Balance();
        if (!somePlace.toString().equals("")
            && !somePrice.toString().equals(""))
        {
            if (sign == '-')
            {

                CURRENT_BALANCE =
                    CURRENT_BALANCE - Float.parseFloat(somePrice.toString());
                somePrice = "-" + somePrice;
            }
            else
            {
                CURRENT_BALANCE =
                    CURRENT_BALANCE + Float.parseFloat(somePrice.toString());
            }
        }

        try
        {

            someBalance.setBalance(CURRENT_BALANCE.toString());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        if (!somePlace.toString().equals("")
            && !somePrice.toString().equals(""))
        {
            Transaction transaction =
                new Transaction(somePlace, somePrice, someBalance);

            transactionArray.add(0, transaction);
            dataSource.insertTransaction(transaction);
            placeDialog.setText("");
            amountDialog.setText("");
            myAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onResume()
    {
        dataSource.open();
        super.onResume();
    }


    @Override
    protected void onPause()
    {
        dataSource.close();
        super.onPause();
    }


    private class DialogListener
        implements OnTouchListener
    {

        public boolean onTouch(View v, MotionEvent e)
        {
            if (e.getAction() == MotionEvent.ACTION_DOWN)
            {
                if (v.getId() == R.id.addTransactionButton && isWithdrawal)
                {
                    addRecord('-');
                    addDialogBox.dismiss();
                }
                if (v.getId() == R.id.addTransactionButton && !isWithdrawal)
                {
                    addRecord('+');
                    addDialogBox.dismiss();
                }
                if (v.getId() == R.id.yesButton)
                {
                    myAlertDialog.dismiss();
                    newBalanceSheetDialog.show();
                }
                if (v.getId() == R.id.noButton)
                {
                    myAlertDialog.dismiss();
                }

            }
            return false;
        }

    }
}
