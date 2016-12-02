package com.company;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account
{
    /*I set this variable to 1 to show how custom exception
      works but it should have no value.*/
    static float balance =1;

    public static void create_account () throws IOException, InputMismatchException
    {

        Scanner input = new Scanner(System.in);

        int choice = 0;


        while (choice != 1 && choice != 2 && choice != 3)
        {

            System.out.println("Would you like to create or look up an account? ");
            System.out.println("1. Look Up an Account");
            System.out.println("2. Create Account");
            System.out.println("3. Return to the Main Menu");

            try{
                choice = input.nextInt();
            }

            catch (InputMismatchException inp)
            {
                System.out.println("Error: Must enter a Number");
                Menu.show_menu();
            }

        }

        if (choice == 3)
        {
            Menu.show_menu();
        }


        String filename = "accounts.dat";

//Create account
     if (choice == 2)
    {
//prompts user for account details and writes them accordingly
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename, true));){

            System.out.println("Enter the user's first name: ");
            String fName = input.nextLine();
            fName = fName.toUpperCase();
            fName += " ";


            System.out.println("Enter the user's last name: ");
            String lName = input.nextLine();
            lName = lName.toUpperCase();
            lName += " ";


            System.out.println("Enter the user's account number: ");
            String aNumber = input.nextLine();
            aNumber += " ";


            System.out.println("Enter the user's account type: ");
            String aType = input.nextLine();
            aType = aType.toUpperCase();
            aType += " ";


            System.out.println("Enter the user's initial deposit: ");
            String iDeposit = input.nextLine();
            iDeposit += " ";

            String write = fName + lName + aNumber + aType + iDeposit;
            output.writeUTF(write);
            output.close();
            Menu.show_menu();

        }
    }

//Look Up account details
    else if (choice == 1)
    {

//queries user for account holder's first and last name
        System.out.println("Enter the Holder's First Name");
        String refFName = input.nextLine();
        refFName = refFName.toUpperCase();

        System.out.println("Enter the Holder's Last Name");
        String refLName = input.nextLine();
        refLName = refLName.toUpperCase();

        boolean found = false;

//creates a string to compare to file content
        String refDetails = refFName + " " + refLName;
        String aDetails = "";

//account details are read from file until the specified user is found, or until the end of the file is reached.
        try (
                DataInputStream read = new DataInputStream(new FileInputStream(filename));
        ){

            while (found == false)
            {
                aDetails = read.readUTF();

                if (aDetails.indexOf(refDetails) != -1)
                {
                    found = true;
                    System.out.println("The account details for that user are: ");

//breaks matching line of text read from file into substrings for output formatting purposes
                    int fNameIndex = aDetails.indexOf(" ");
                    String fName = aDetails.substring(0, fNameIndex);

                    int lNameIndex = aDetails.indexOf(" ", ++fNameIndex);
                    String lName = aDetails.substring(fNameIndex, lNameIndex);

                    int aNumberIndex = aDetails.indexOf(" ", ++lNameIndex);
                    String aNumber = aDetails.substring(lNameIndex, aNumberIndex);

                    int aTypeIndex = aDetails.indexOf(" ", ++aNumberIndex);
                    String aType = aDetails.substring(aNumberIndex, aTypeIndex);

                    String iDeposit = aDetails.substring(aTypeIndex + 1);

//displays account details
                    System.out.println("First Name: " + fName);
                    System.out.println("Last Name: " + lName);
                    System.out.println("Account Number: " + aNumber);
                    System.out.println("Account Type: " + aType);
                    System.out.println("Initial Deposit: " + "$" + iDeposit);
                    System.out.println("");
                    Menu.show_menu();
                }
            }
        }
        catch (EOFException ex)
        {
            System.out.println("User Not Found.");
            Menu.show_menu();
        }
    }

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
