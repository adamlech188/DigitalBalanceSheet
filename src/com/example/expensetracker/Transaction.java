package com.example.expensetracker;

import java.util.Date;
import java.text.DateFormat;

// -------------------------------------------------------------------------
/**
 * This class will contain the given transaction.
 *
 * @author Adam Lech
 * @version Jun 19, 2013
 */
public class Transaction
{
    private CharSequence currentDate;
    private CharSequence place;
    private CharSequence withdrawal;
    private Balance      currentBalance;
    private Long         id;


    // ----------------------------------------------------------
    /**
     * Create a new Transaction object.
     *
     * @param place
     * @param withdrawal
     * @param currentBalance
     */
    public Transaction(
        CharSequence place,
        CharSequence withdrawal,
        Balance currentBalance)
    {
        this.place = place;
        this.withdrawal = withdrawal;
        this.currentBalance = currentBalance;
        final DateFormat df = DateFormat.getDateInstance();
        currentDate = df.format(new Date());

    }


    // ----------------------------------------------------------
    /**
     * Gets current date.
     *
     * @return String currentDate;
     */
    public CharSequence getCurrentDate()
    {
        return currentDate;
    }


    // ----------------------------------------------------------
    /**
     * Gets place.
     *
     * @return String place
     */
    public CharSequence getPlace()
    {
        return place;

    }


    // ----------------------------------------------------------
    /**
     * Gets withdrawal.
     *
     * @return withdrawal
     */
    public CharSequence getWithdrawal()
    {
        return format(withdrawal);
    }


    // ----------------------------------------------------------
    /**
     * Gets current balance.
     *
     * @return current balance.
     * @throws Exception
     */
    public String getBalance()
        throws Exception
    {

        return currentBalance.getBalance();

    }


    private String format(CharSequence somePrice)
    {

        String returnValue;
        if (somePrice.length() == 1)
        {
            returnValue =
                "$"
                    + String.format(
                        "%.2f",
                        Float.parseFloat(somePrice.toString()));
        }
        else if (somePrice.charAt(0) != '$' && somePrice.charAt(1) != '$')
        {
            if (somePrice.charAt(0) == '-' && somePrice.charAt(1) != '$')
            {
                returnValue =
                    "-$"
                        + String
                            .format("%.2f", Float.parseFloat(somePrice
                                .toString().substring(1)));
            }
            else
            {
                returnValue =
                    "$"
                        + String.format(
                            "%.2f",
                            Float.parseFloat(somePrice.toString()));
            }
        }

        else
        {
            returnValue = somePrice.toString();
        }
        return returnValue;
    }


    // ----------------------------------------------------------
    /**
     * Sets id.
     *
     * @param id
     */
    public void setId(Long id)
    {
        this.id = id;
    }


    // ----------------------------------------------------------
    /**
     * Gets id.
     *
     * @return
     */
    public Long getId()
    {
        return id;
    }


    public void setDate(CharSequence currentDate)
    {
        this.currentDate = currentDate;
    }
}
