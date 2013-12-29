package com.example.datatable;

import android.util.Log;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// -------------------------------------------------------------------------
/**
 * This my SQLiteHelper
 * 
 * @author Adam
 * @version Aug 5, 2013
 */
public class MySQLiteHelper extends SQLiteOpenHelper

{
	/**
	 * Name of transaction table
	 */
	public static final String TABLE_TRANSACTIONS = "transaction_table";

	/**
	 * Name of most frequently used places table
	 * 
	 */
	public static final String TABLE_FREQUENT_PLACES = "freq_places";

	/**
	 * Name of minimum frequency trigger
	 */
	public static final String TRIGGER_MIN_FREQ = "min_freq";
	/**
	 * Id column
	 */
	public static final String COLUMN_ID = "_id";
	/**
	 * Date column
	 */
	public static final String COLUMN_DATE = "currentDate";
	/**
	 * Place column
	 */
	public static final String COLUMN_PLACE = "place";
	/**
	 * Transaction column
	 */
	public static final String COLUMN_TRANSACTION = "operation";
	/**
	 * Balance column
	 */
	public static final String COLUMN_BALANCE = "balance";
	/**
	 * Database name column
	 */
	public static final String DATABASE_NAME = "transactions.db";
	/**
	 * Version column
	 */
	public static final int DATABASE_VERSION = 14;

	// Creating table to store last 20 most frequently used
	// places of transaction.
	/**
	 * Name of the column with the frequency number
	 */
	public static final String COLUMN_FREQEUNCY = "_freq";

	/**
	 * Create statements for sql
	 */
	public static final String DATABASE_CREATE = "CREATE TABLE "
			+ TABLE_TRANSACTIONS + " (" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " TEXT, "
			+ COLUMN_PLACE + " TEXT, " + COLUMN_TRANSACTION + " TEXT, "
			+ COLUMN_BALANCE + " TEXT);";
	public static final String FREQ_TABLE_CREATE = "CREATE TABLE "
			+ TABLE_FREQUENT_PLACES + "(" + COLUMN_FREQEUNCY + " INTEGER, "
			+ COLUMN_PLACE + " TEXT PRIMARY KEY);";

	/**
	 * Create statement to create min_freq_trigger
	 */
	public static final String MIN_FREQ_TRIGGER_CREATE = "CREATE TRIGGER "
			+ TRIGGER_MIN_FREQ + " BEFORE INSERT ON " + TABLE_FREQUENT_PLACES
			+ " FOR EACH ROW WHEN 20 <= (SELECT COUNT(*) FROM "
			+ TABLE_FREQUENT_PLACES + ")" + " BEGIN DELETE FROM "
			+ TABLE_FREQUENT_PLACES + " WHERE " + COLUMN_PLACE + " IN (SELECT "
			+ COLUMN_PLACE + " FROM " + TABLE_FREQUENT_PLACES + " ORDER BY "
			+ COLUMN_FREQEUNCY + " LIMIT 1);" + "END;";

	// ----------------------------------------------------------
	/**
	 * Create a new MySQLiteHelper object.
	 * 
	 * @param context
	 */
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		db.execSQL(FREQ_TABLE_CREATE);
		db.execSQL(MIN_FREQ_TRIGGER_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FREQUENT_PLACES);
		db.execSQL("DROP TABLE IF EXISTS sometable");
		onCreate(db);

	}

}
