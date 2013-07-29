package com.example.expensetracker;

// -------------------------------------------------------------------------
/**
 * This class will store the current balance.
 *
 * @author Adam
 * @version Jun 22, 2013
 */
public class Balance
{

    Float balance;


    // ----------------------------------------------------------
    /**
     * Constructor Create a new Balance object.
     */
    public Balance()
    {
        balance = null;

    }


    // ----------------------------------------------------------
    /**
     * Gets string representation of balance;
     *
     * @return balance
     * @throws Exception
     *             - when balance is not set
     */
    public String getBalance()
        throws Exception
    {
        if (balance == null)
        {
            throw new Exception("You have to set balance first.");
        }
        else
        {
            return format(balance.toString());
        }
    }


    // ----------------------------------------------------------
    /**
     * Sets current balance.
     *
     * @param balSequence
     */
    public void setBalance(CharSequence balSequence)
    {
        balance = Float.parseFloat(balSequence.toString());
    }


    // ----------------------------------------------------------
    /**
     * Adds given amount to the balance.
     *
     * @param credit
     * @return balance - current balance
     */
    public String Credit(CharSequence credit)
    {
        balance = balance + Float.parseFloat(credit.toString());
        return format(balance.toString());
    }


    // ----------------------------------------------------------
    /**
     * Adds given amount to the balance.
     *
     * @param withdrawal
     * @return balance - current balance
     */
    public String Withdrawal(CharSequence withdrawal)
    {
        balance = balance - Float.parseFloat(withdrawal.toString());
        return format(balance.toString());
    }


    private String format(String somePrice)
    {

        String returnValue;
        if (somePrice.charAt(0) == '-')
        {
            returnValue =
                "-$"
                    + String.format(
                        "%.2f",
                        Float.parseFloat(somePrice.substring(1)));
        }
        else
        {

            returnValue =
                "$" + String.format("%.2f", Float.parseFloat(somePrice));
        }
        return returnValue;
    }

}
