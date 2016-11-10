package com.company;

public class Account
{
    /*I set this variable to 1 to show how custom exception
      works but it should have no value.*/
    static float balance =1;

    public static void create_account ()
    {
        //Ed work on this method.
    }

    public static void deposit (double n)
    {
        //Kevin work on this method.
    }

    public static void withdraw(double n) throws WithdrawLimitException
    {
        //Kevin work on this method. Note: Do not change if else block below.

        if (n <= balance)
        {
            balance -= n;
        }
        else
        {
            double short_by = n-balance;
            throw new WithdrawLimitException(short_by);
        }
    }

    public static void show_balance ()
    {
        System.out.println("Balance: " + balance);
    }

    public static void edit_account ()
    {
        //I will create this method once Ed is done with create_account
    }

    public static void exit ()
    {
        //Madelin work on this method. Start the project documentation and write on what we have completed so far.
        System.out.println("Thank you for using Simple Banking System.");
    }
}
