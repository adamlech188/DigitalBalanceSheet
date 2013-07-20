package com.example.datatable;

import android.util.Log;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper
    extends SQLiteOpenHelper

{
    public static final String TABLE_TRANSACTIONS = "sometable";
    public static final String COLUMN_ID          = "_id";
    public static final String COLUMN_DATE        = "currentDate";
    public static final String COLUMN_PLACE       = "place";
    public static final String COLUMN_TRANSACTION = "operation";
    public static final String COLUMN_BALANCE     = "balance";
    public static final String DATABASE_NAME      = "transactions.db";
    public static final int    DATABASE_VERSION   = 9;

    public static final String DATABASE_CREATE    =
                                                      "CREATE TABLE "
                                                          + TABLE_TRANSACTIONS
                                                          + " ("
                                                          + COLUMN_ID
                                                          + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                          + COLUMN_DATE
                                                          + " TEXT, "
                                                          + COLUMN_PLACE
                                                          + " TEXT, "
                                                           + COLUMN_TRANSACTION
                                                          + " TEXT, "
                                                          + COLUMN_BALANCE
                                                          + " TEXT);";


    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(
            MySQLiteHelper.class.getName(),
            "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        onCreate(db);

    }

}
