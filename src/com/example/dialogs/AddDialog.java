package com.example.dialogs;

import android.widget.ArrayAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import com.example.expensetracker.R;
import android.widget.EditText;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.TextView;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

// -------------------------------------------------------------------------
/**
 * Rainbow dialog class.
 *
 * @author Adam
 * @version Jul 6, 2013
 */
public class AddDialog
    extends Dialog
    implements OnTouchListener
{
    private AutoCompleteTextView placeField;
    private EditText             amountField;
    private Button               addTransactionButton;
    private Button               cancelButton;
    private Context              currentContext;
    private TextView             titleView;
    private final String[]       frequentPlaces = { "Walmart", "McDonald's",
        "Giant", "Sodex", "ATM"                };


    // ----------------------------------------------------------
    /**
     * Constructor
     *
     * @param context
     */
    public AddDialog(Context context)
    {
        super(context);
        this.currentContext = context;

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
        setContentView(R.layout.adddialog);

        // Buttons
        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnTouchListener(this);

        addTransactionButton = (Button)findViewById(R.id.addTransactionButton);
        addTransactionButton.setOnTouchListener(this);

        placeField = (AutoCompleteTextView)findViewById(R.id.placeDialog);
        TextWatcher myTextWatcher = new MyTextWatcher();
        placeField.addTextChangedListener(myTextWatcher);
        ArrayAdapter<String> myAdapter =
            new ArrayAdapter<String>(
                currentContext,
                android.R.layout.simple_dropdown_item_1line,
                frequentPlaces);
        placeField.setAdapter(myAdapter);
        amountField = (EditText)findViewById(R.id.amountDialog);

        // Title
        titleView = (TextView)findViewById(R.id.titleView);

    }


    public boolean onTouch(View v, MotionEvent e)
    {
        if (e.getAction() == MotionEvent.ACTION_DOWN)
        {
            if (v.getId() == R.id.cancelButton)
            {
                dismiss();
                return true;
            }

        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Gets add transaction button.
     *
     * @return addTransactionButton
     */
    public Button getaddTransactionButton()
    {
        if (this.addTransactionButton != null)
        {
            return this.addTransactionButton;
        }
        else
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets place Field
     *
     * @return placeField.
     */
    public EditText getplaceField()
    {
        return this.placeField;
    }


    // ----------------------------------------------------------
    /**
     * Gets amount field
     *
     * @return amount field.
     */
    public EditText getamountField()
    {
        return this.amountField;
    }


    public void setTitle(CharSequence title)
    {
        titleView.setText(title);
    }


    private class MyTextWatcher
        implements TextWatcher
    {

        public void afterTextChanged(Editable s)
        {
            // TODO Auto-generated method stub

        }


        public void beforeTextChanged(
            CharSequence s,
            int start,
            int count,
            int after)
        {
            // TODO Auto-generated method stub

        }


        public void onTextChanged(
            CharSequence s,
            int start,
            int before,
            int count)
        {
            // TODO Auto-generated method stub

        }

    }
}
