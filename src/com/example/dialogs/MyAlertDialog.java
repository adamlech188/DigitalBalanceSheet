package com.example.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import com.example.expensetracker.R;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.content.Context;
import android.app.Dialog;

// -------------------------------------------------------------------------
/**
 * This class defines my alert box.
 *
 * @author Adam
 * @version Jul 14, 2013
 */
public class MyAlertDialog
    extends Dialog

{
    /**
     * Yes button
     */
    Button yesButton;
    /**
     * No button
     */
    Button noButton;


    // ----------------------------------------------------------
    /**
     * Create a new MyAlertDialog object.
     *
     * @param context
     */
    public MyAlertDialog(Context context)
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
    }


    // ----------------------------------------------------------
    /**
     * Gets yesButton
     *
     * @return yesButton
     */
    public Button getYesButton()
    {
        return this.yesButton;
    }


    // ----------------------------------------------------------
    /**
     * Gets noButton
     *
     * @return noButton
     */
    public Button getNoButton()
    {
        return this.noButton;
    }

}
