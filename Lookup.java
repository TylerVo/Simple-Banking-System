package com.company;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lookup extends Account {


    public static String fName;
    public static String lName;
    public static String aNumber;
    public static String aType;
    public static String iDeposit;


    public static void lookup_account() throws IOException, InputMismatchException
{
    Scanner input = new Scanner(System.in);

    //queries user for account holder's first and last name
    System.out.println("Enter the Holder's First Name");
    String refFName = input.nextLine();
    refFName = refFName.toLowerCase();

    System.out.println("Enter the Holder's Last Name");
    String refLName = input.nextLine();
    refLName = refLName.toLowerCase();

    boolean found = false;

    //creates a string to compare to file content
    String refDetails = refFName;
    String aDetails = "";

  //account details are read from file until the specified user is found, or until the end of the file is reached.
    String filename = "accounts.dat";

    try (
    DataInputStream read = new DataInputStream(new FileInputStream(filename));
        )

    {

        while (found == false) {
            aDetails = read.readUTF();

            if (aDetails.indexOf(refDetails) != -1) {
                found = true;
                System.out.println("The account details for that user are: ");

//breaks matching line of text read from file into substrings for output formatting purposes
                int fNameIndex = aDetails.indexOf(" ");
                fName = aDetails.substring(0, fNameIndex);

                int lNameIndex = aDetails.indexOf(" ", ++fNameIndex);
                lName = aDetails.substring(fNameIndex, lNameIndex);

                int aNumberIndex = aDetails.indexOf(" ", ++lNameIndex);
                aNumber = aDetails.substring(lNameIndex, aNumberIndex);

                int aTypeIndex = aDetails.indexOf(" ", ++aNumberIndex);
                aType = aDetails.substring(aNumberIndex, aTypeIndex);

                iDeposit = aDetails.substring(aTypeIndex + 1);

//displays account details
                System.out.println("First Name: " + fName);
                System.out.println("Last Name: " + lName);
                System.out.println("Account Number: " + aNumber);
                System.out.println("Account Type: " + aType);
                System.out.println("Initial Deposit: " + "$" + iDeposit);
                System.out.println("");
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
