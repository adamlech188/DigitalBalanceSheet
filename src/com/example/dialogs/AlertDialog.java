package com.example.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.example.expensetracker.R;

// -------------------------------------------------------------------------
/**
 * This class defines my alert box.
 *
 * @author Adam
 * @version Jul 14, 2013
 */
public class AlertDialog
    extends Dialog

{
    /**
     * Yes button
     */
    Button              yesButton;
    /**
     * No button
     */
    Button              noButton;
    /**
     * Warning text view
     */
    TextView            warningTextView;
    /**
     * Warning text for delete button
     */
    public final String WARNING_DELETE_TEXT         =
                                                        "You are about to delete the most recent transaction.\n\n Are you sure you want to continue ?  ";
    /**
     * Warning text for new balance sheet.
     */
    public final String WARNING_NEW_BALANCE_TEXT    =
                                                        "Setting new balance sheet will erase all saved records. \n\n Are you sure you want to proceed ?";
    /**
     * Warning text for empty balance sheet.
     */
    public final String WARNING_EMPTY_BALANCE_SHEET =
                                                        "Your balance sheet is empty. \n\n Would you like to set a new one? ";
    /**
     * Warning text for erasing balance sheet.
     */
    public final String WARNING_ERASE_BALANCE_SHEET =
                                                        "You are about to erase your current balance sheet. \n\n Are you sure you want to continue ?";

    private boolean     isDeleteButton              = false;
    private boolean     isEmptyBalanceSheet         = false;
    private boolean     isNewBalanceSheet           = false;
    private boolean     isEraseBalanceSheet         = false;


    // ----------------------------------------------------------
    /**
     * Create a new MyAlertDialog object.
     *
     * @param context
     */
    public AlertDialog(Context context)
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
        setContentView(R.layout.my_alert_dialog);
        yesButton = (Button)findViewById(R.id.yesButton);
        noButton = (Button)findViewById(R.id.noButton);
        warningTextView = (TextView)findViewById(R.id.warning);
        warningTextView.setText("Are sure ? ");

    }


    // ----------------------------------------------------------
    /**
     * Gets yesButton
     *
     * @param ontouchlistener
     */
    public void setYesButtonOnTouchListener(OnTouchListener ontouchlistener)
    {
        yesButton.setOnTouchListener(ontouchlistener);
    }


    // ----------------------------------------------------------
    /**
     * Gets noButton
     *
     * @param ontouchlistener
     */
    public void setNoButtonOnTouchListener(OnTouchListener ontouchlistener)
    {
        noButton.setOnTouchListener(ontouchlistener);
    }


    // ----------------------------------------------------------
    /**
     * Sets text of the alert dialog for delete button.
     *
     * @param isDeleteButton
     */
    public void setDeleteRecentTransation(boolean isDeleteButton)
    {
        warningTextView.setText(WARNING_DELETE_TEXT);
        this.isDeleteButton = isDeleteButton;
        this.isEmptyBalanceSheet = false;
        this.isNewBalanceSheet = false;
        this.isEraseBalanceSheet = false;
    }


    // ----------------------------------------------------------
    /**
     * Gets delete button flag.
     *
     * @return isDelete
     */
    public boolean isDeleteButton()
    {
        return this.isDeleteButton;
    }


    // ----------------------------------------------------------
    /**
     * Sets if the alert will be for new balance.
     *
     * @param isNewBalanceSheet
     */
    public void setNewSheetBalance(boolean isNewBalanceSheet)
    {
        warningTextView.setText(WARNING_NEW_BALANCE_TEXT);
        this.isNewBalanceSheet = isNewBalanceSheet;
        this.isDeleteButton = false;
        this.isEmptyBalanceSheet = false;
        this.isEraseBalanceSheet = false;

    }


    // ----------------------------------------------------------
    /**
     * Gets new balance sheet flag.
     *
     * @return isNewBalanceSheet
     */
    public boolean isNewBalanceSheet()
    {
        return this.isNewBalanceSheet;
    }


    // ----------------------------------------------------------
    /**
     * Sets text of alert dialog for empty sheet balance warning.
     *
     * @param isEmptyBalanceSheet
     */
    public void setEmptySheetWarning(boolean isEmptyBalanceSheet)
    {
        warningTextView.setText(WARNING_EMPTY_BALANCE_SHEET);
        this.isEmptyBalanceSheet = isEmptyBalanceSheet;
        this.isNewBalanceSheet = false;
        this.isDeleteButton = false;
        this.isEraseBalanceSheet = false;

    }


    // ----------------------------------------------------------
    /**
     * Gets empty balance sheet.
     *
     * @return isEmptyBalanceSheet
     */
    public boolean isEmptyBalanceSheet()
    {
        return this.isEmptyBalanceSheet;
    }


    // ----------------------------------------------------------
    /**
     * Sets text for erase balance dialog.
     *
     * @param isEraseBalanceSheet
     */
    public void setEraseBalanceSheet(boolean isEraseBalanceSheet)
    {
        warningTextView.setText(WARNING_ERASE_BALANCE_SHEET);
        this.isEraseBalanceSheet = isEraseBalanceSheet;
        this.isDeleteButton = false;
        this.isEmptyBalanceSheet = false;
        this.isNewBalanceSheet = false;
    }


    // ----------------------------------------------------------
    /**
     * Gets erase balance sheet flag;
     *
     * @return isEraseBalanceSheet
     */
    public boolean isEraseBalanceSheet()
    {
        return this.isEraseBalanceSheet;
    }

}
