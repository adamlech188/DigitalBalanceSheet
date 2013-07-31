package com.example.datatable;

import com.example.expensetracker.Balance;
import com.example.expensetracker.Transaction;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.SQLException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

// -------------------------------------------------------------------------
/**
 * @author Adam
 * @version Jun 29, 2013
 */
public class TransactionsDataSource
{
    private SQLiteDatabase dataBase;
    private MySQLiteHelper dbHelper;
    private String[]       allColumns = { MySQLiteHelper.COLUMN_ID,
        MySQLiteHelper.COLUMN_DATE, MySQLiteHelper.COLUMN_PLACE,
        MySQLiteHelper.COLUMN_TRANSACTION, MySQLiteHelper.COLUMN_BALANCE };


    // ----------------------------------------------------------
    /**
     * Transaction data source.
     *
     * @param context
     */
    public TransactionsDataSource(Context context)
    {
        dbHelper = new MySQLiteHelper(context);
    }


    public void open()
        throws SQLException
    {
        dataBase = dbHelper.getWritableDatabase();

    }


    public void close()
    {
        dataBase.close();

    }


    public void insertTransaction(Transaction transaction)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DATE, transaction.getCurrentDate()
            .toString());
        values.put(MySQLiteHelper.COLUMN_PLACE, transaction.getPlace()
            .toString());
        values.put(MySQLiteHelper.COLUMN_TRANSACTION, transaction
            .getWithdrawal().toString());
        try
        {
            values.put(MySQLiteHelper.COLUMN_BALANCE, transaction.getBalance());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long insertId =
            dataBase.insert(MySQLiteHelper.TABLE_TRANSACTIONS, null, values);

        transaction.setId(insertId);

    }


    public void deleteTransaction(Transaction transaction)
    {
        long tranId = transaction.getId();
        dataBase.delete(
            MySQLiteHelper.TABLE_TRANSACTIONS,
            MySQLiteHelper.COLUMN_ID + " = " + tranId,
            null);

    }


    public List<Transaction> getAllTransaction()
    {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Cursor cursor =
            dataBase.query(
                MySQLiteHelper.TABLE_TRANSACTIONS,
                allColumns,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Transaction transaction = cursorToTransaction(cursor);
            transactions.add(0, transaction);
            cursor.moveToNext();

        }
        // make sure to close the cursor;
        cursor.close();
        return transactions;

    }


    public void truncate()
    {
        dataBase.delete(MySQLiteHelper.TABLE_TRANSACTIONS, null, null);
    }


    private Transaction cursorToTransaction(Cursor cursor)
    {
        Balance balance = new Balance();
        String stringCursor = cursor.getString(4);
        if (stringCursor.charAt(0) == '-')
        {
            stringCursor = stringCursor.charAt(0) + stringCursor.substring(2);
            balance.setBalance(stringCursor);
        }
        else
        {
            balance.setBalance(stringCursor.substring(1));
        }
        Transaction transaction =
            new Transaction(cursor.getString(2), cursor.getString(3), balance);
        transaction.setDate(cursor.getString(1));
        transaction.setId(Long.parseLong(cursor.getString(0)));
        return transaction;
    }

}
