package com.company;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import static com.company.Lookup.*;

public class Account
{


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
                String fName = input.next();
                fName = fName.toLowerCase();
                fName += " ";
                if ((fName.length() > (20))) { //First Name Length check
                    System.out.println("First name cannot have more than 20 characters");
                    create_account();
                }

                System.out.println("Enter the user's last name: ");
                String lName = input.next();
                lName = lName.toLowerCase();
                lName += " ";
                if ((lName.length() > (20))) { //Last Name Length check
                    System.out.println("Last name cannot have more than 20 characters");
                    create_account();
                }

                System.out.println("Enter the user's account number: ");
                String aNumber = input.next();
                aNumber += " ";
                if (!(aNumber.length() == (10))) { //Account number Length check
                    System.out.println("Bank account must be 9 digits long.");
                    create_account();
                }

                System.out.println("Enter the user's account type: ");
                String aType = input.next();
                aType = aType.toUpperCase();
                aType += " ";

                System.out.println("Enter the user's initial deposit: ");
                String iDeposit = input.next();
                iDeposit += " ";

                String write = fName + lName + aNumber + aType + iDeposit;
                output.writeUTF(write);
                output.close();
                System.out.println("Account created successfully.");
                Menu.show_menu();

            }
        }

        //Look Up account details
        else if (choice == 1)
        {
            Lookup.lookup_account();
            Lookup.display_account();
            Menu.show_menu();
        }

        input.close();

    }

    public static void deposit (double n)
    {
        try
        {
            Lookup.lookup_account();

                aBalance = aBalance + n;
                String dataNew = String.valueOf(aBalance);
                edit_account();

                //Display deposit results
                System.out.println("Account Holder: " + fName + " " + lName);
                display_balance();


            } catch (IOException e) {
        }
    }

    public static void withdraw(double n) throws WithdrawLimitException, IOException {

        Lookup.lookup_account();

       /* if (n <= aBalance)
        {
            aBalance -= n;
        }
        else
        {
            double short_by = n-aBalance;
            throw new WithdrawLimitException(short_by);
        }

        */
    }

    public static void show_balance () throws IOException {
        Lookup.display_balance();
    }

    public static void edit_account ()  throws IOException, InputMismatchException, NoSuchElementException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the old first name:" );
        String firstName=input.next();
        System.out.println("Enter the old last name:" );
        String lastName=input.next();
        System.out.println("Enter the old bank account number");
        String accountNumber=input.next();
        String dataOld = firstName+" "+lastName+" "+accountNumber;
        System.out.println("Enter the new first name:" );
        firstName=input.next();
        System.out.println("Enter the new last name:" );
        lastName=input.next();
        System.out.println("Enter the new bank account number");
        accountNumber=input.next();
        String dataNew = firstName+" "+lastName+" "+accountNumber;

        try { // To keep system from crashing.

            ArrayList<String> data = new ArrayList<String>();
            // Opening the stream for reading.
            DataInputStream stream = new DataInputStream(new FileInputStream("accounts.dat"));
            try{ // if it is end of file, then throw exception.
                while(true){ // Runs until break.
                    String temp = stream.readUTF(); // read utf.
                    if(temp == null) // if data read is null then
                        break;
                    data.add(temp); // else add the string to array list.
                }
            }catch(Exception e){
            }
            stream.close();

            boolean found = false; // to check is data found or not.
            // Iterate through all data to check if it contains data or not.
            for(int i = 0; i < data.size(); i++){
                if(data.get(i).contains(dataOld)){
                    found = true;
                    data.set(i, data.get(i).replace(dataOld, dataNew)); // replace the data.
                }
            }

            if(!found){ // if it is not found then.
                System.out.println("Cant't found the user with data: "+dataOld); // print the data.
            }else{ // re-write the file.

                // open a file.
                File file = new File("accounts.dat");
                if(file.exists())
                    file.delete();

                // create a new file and open the output stream.
                DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
                for(int i = 0; i < data.size(); i++){
                    String text = data.get(i);
                    outputStream.writeUTF(text);
                }
                outputStream.close();

                System.out.println("Successfully Updated the Data.");

            }

        } catch (NoSuchElementException e) {
            System.out.println("Problem reading file.");
        }
    }

    public static void exit ()
    {

        System.out.println("Thank you for using Simple Banking System.");
        System.exit(0);
    }
}
