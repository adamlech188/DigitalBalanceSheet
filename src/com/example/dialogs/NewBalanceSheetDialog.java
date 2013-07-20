package com.example.dialogs;

import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Button;
import com.example.expensetracker.R;
import android.content.Context;
import android.app.Dialog;

// -------------------------------------------------------------------------
/**
 * New balance sheet dialog. It let the user enter new balance.
 *
 * @author Adam
 * @version Jul 15, 2013
 */
public class NewBalanceSheetDialog
    extends Dialog
{

    EditText balanceField;
    Button   createNewSheetButton;
    Button   cancelButton;


    // ----------------------------------------------------------
    /**
     * Constructor
     *
     * @param context
     */
    public NewBalanceSheetDialog(Context context)
    {
        super(context);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_balance_sheet_dialog);

        balanceField = (EditText)findViewById(R.id.newBalanceField);
        createNewSheetButton = (Button)findViewById(R.id.new_table_button);
        cancelButton = (Button)findViewById(R.id.cancelButton);
    }


    // ----------------------------------------------------------
    /**
     * Sets on touch listener for New Balance Sheet button
     *
     * @param ontouchlistener
     */
    public void setCreateNewBalanceSheetOnTouchListener(
        OnTouchListener ontouchlistener)
    {
        createNewSheetButton.setOnTouchListener(ontouchlistener);
    }


    // ----------------------------------------------------------
    /**
     * Sets on touch listener for cancel button
     *
     * @param ontouchlistener
     */
    public void setCancelButtonOnTouchListener(OnTouchListener ontouchlistener)
    {
        cancelButton.setOnTouchListener(ontouchlistener);
    }
}
