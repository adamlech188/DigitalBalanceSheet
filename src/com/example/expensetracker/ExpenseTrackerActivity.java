package com.example.expensetracker;

import com.example.expensetracker.MyAdapter;
import com.example.expensetracker.Balance;
import com.example.expensetracker.Transaction;
import com.example.dialogs.NewBalanceSheetDialog;
import com.example.dialogs.AlertDialog;
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
    private Float                  CURRENT_BALANCE = null;
    private AlertDialog            myAlertDialog;

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

        myAlertDialog = new AlertDialog(this);
        newBalanceSheetDialog = new NewBalanceSheetDialog(this);

        dataSource.open();

        monthsListView = (ListView)findViewById(R.id.months_list);
        transactionArray = dataSource.getAllTransaction();
        addDialogBox.setLastTwentyPlaces(this.getLastTwentyPlaces());
        if (!transactionArray.isEmpty())
        {
            try
            {
                CURRENT_BALANCE =
                    transactionArray.get(0).getCurrentBalance()
                        .getFloatBalance();
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
        if (transactionArray.size() > 0)
        {
            transactionArray.remove(transactionArray.size() - 1);

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
            if (CURRENT_BALANCE == null)
            {

                newBalanceSheetDialog.show();
                DialogListener dialogListener = new DialogListener();
                newBalanceSheetDialog
                    .setCreateNewBalanceSheetOnTouchListener(dialogListener);
                newBalanceSheetDialog
                    .setCancelButtonOnTouchListener(dialogListener);
            }
            else
            {
                myAlertDialog.show();
                myAlertDialog.setNewSheetBalance(true);
                DialogListener dialogListener = new DialogListener();
                myAlertDialog.setYesButtonOnTouchListener(dialogListener);
                myAlertDialog.setNoButtonOnTouchListener(dialogListener);
            }

        }

        if (item.getItemId() == R.id.erase_sheet && CURRENT_BALANCE != null)
        {
            myAlertDialog.show();
            myAlertDialog.setEraseBalanceSheet(true);
            DialogListener dialogListener = new DialogListener();
            myAlertDialog.setYesButtonOnTouchListener(dialogListener);
            myAlertDialog.setNoButtonOnTouchListener(dialogListener);

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
        if (CURRENT_BALANCE != null)
        {
            isWithdrawal = true;
            addDialogBox.show();
            addDialogBox.setTitle("WITHDRAWAL");
            Button addTransactionButton =
                addDialogBox.getaddTransactionButton();
            OnTouchListener dialogListener = new DialogListener();
            addTransactionButton.setOnTouchListener(dialogListener);
        }

    }


    // ----------------------------------------------------------
    /**
     * Deletes most recent transaction.
     *
     * @param view
     */
    public void deleteButtonClicked(View view)
    {

        if (transactionArray.size() > 0)
        {
            myAlertDialog.show();
            myAlertDialog.setDeleteRecentTransation(true);
            DialogListener dialogListener = new DialogListener();
            myAlertDialog.setYesButtonOnTouchListener(dialogListener);
            myAlertDialog.setNoButtonOnTouchListener(dialogListener);

        }
    }


    // ----------------------------------------------------------
    /**
     * Adds deposit transaction.
     *
     * @param view
     */
    public void depositButtonClicked(View view)
    {
        if (CURRENT_BALANCE != null)
        {
            isWithdrawal = false;
            addDialogBox.show();
            addDialogBox.setTitle("DEPOSIT");
            Button addTransactionButton =
                addDialogBox.getaddTransactionButton();
            OnTouchListener dialogListener = new DialogListener();
            addTransactionButton.setOnTouchListener(dialogListener);
        }

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
                if (v.getId() == R.id.yesButton
                    && myAlertDialog.isNewBalanceSheet()
                    && CURRENT_BALANCE != null)
                {
                    myAlertDialog.dismiss();
                    newBalanceSheetDialog.show();
                    newBalanceSheetDialog.setCancelButtonOnTouchListener(this);
                    newBalanceSheetDialog
                        .setCreateNewBalanceSheetOnTouchListener(this);
                }
                if (v.getId() == R.id.yesButton
                    && myAlertDialog.isNewBalanceSheet()
                    && CURRENT_BALANCE == null)
                {
                    newBalanceSheetDialog.show();
                    newBalanceSheetDialog.setCancelButtonOnTouchListener(this);
                    newBalanceSheetDialog
                        .setCreateNewBalanceSheetOnTouchListener(this);

                }
                if (v.getId() == R.id.yesButton
                    && myAlertDialog.isDeleteButton())
                {
                    myAlertDialog.dismiss();
                    Transaction removedTransaction = transactionArray.remove(0);
                    if (transactionArray.size() == 0)
                    {
                        CURRENT_BALANCE = null;
                    }
                    else
                    {
                        CURRENT_BALANCE =
                            transactionArray.get(0).getCurrentBalance()
                                .getFloatBalance();
                    }
                    dataSource.deleteTransaction(removedTransaction);
                    myAdapter.notifyDataSetChanged();
                }
                if (v.getId() == R.id.yesButton
                    && myAlertDialog.isEraseBalanceSheet())
                {
                    dataSource.truncate();
                    transactionArray.clear();
                    CURRENT_BALANCE = null;
                    myAdapter.notifyDataSetChanged();
                    myAlertDialog.dismiss();
                }
                if (v.getId() == R.id.noButton)
                {
                    myAlertDialog.dismiss();
                }
                if (v.getId() == R.id.new_table_button)
                {
                    if (newBalanceSheetDialog.getNewBalance() != null)
                    {
                        CURRENT_BALANCE = newBalanceSheetDialog.getNewBalance();
                        dataSource.truncate();
                        transactionArray.removeAll(transactionArray);
                        Balance currentBalance = new Balance();
                        currentBalance.setBalance(CURRENT_BALANCE.toString());
                        Transaction invisibleTransaction =
                            new Transaction("Invisible", "0", currentBalance);
                        dataSource.insertTransaction(invisibleTransaction);
                        myAdapter.notifyDataSetChanged();
                    }
                    newBalanceSheetDialog.dismiss();

                }
                if (v.getId() == R.id.cancelButton)
                {
                    newBalanceSheetDialog.dismiss();
                }

            }
            return false;
        }

    }


    // ----------------------------------------------------------
    /**
     * Gets a string of last twenty or less places.
     *
     * @param transactionArray
     * @return
     */
    private ArrayList<String> getLastTwentyPlaces()
    {
        ArrayList<String> twentyplacesList = new ArrayList<String>();
        for (int i = 0; i < 21 && i < transactionArray.size(); i++)
        {
            String place = transactionArray.get(i).getPlace().toString();
            if (!twentyplacesList.contains(place))
            {
                twentyplacesList.add(place);
            }
        }
        return twentyplacesList;
    }

}
