package com.company;
import java.io.IOException;
import java.util.Scanner;

// Simple Banking System V 1.0

public class Menu
{
    static Account acc;

    public static void main(String[] args) throws IOException {

        //Create Objects
        acc = new Account();

        Scanner scan = new Scanner(System.in);


        System.out.println("Welcome to the Simple Banking System.\n Please choose from the following options: ");

        Boolean fl = true;

        while (fl) {


            show_menu();

            System.out.println("Would you like to continue? Yes/No: ");

            String choice = scan.next();
            if (choice.equalsIgnoreCase("yes") ||
                    (choice.equalsIgnoreCase("y")))
            {
                fl = true;
            }
            else if (choice.equalsIgnoreCase("no")||
                    (choice.equalsIgnoreCase("n")))
            {
                fl = false;
            }
            else
            {
                System.out.println("Invalid option. ");

            }
        }
    }

    public static void show_menu() throws IOException {
        System.out.println("1\t Create Account");
        System.out.println("2\t Deposit");
        System.out.println("3\t Withdraw");
        System.out.println("4\t Show Balance");
        System.out.println("5\t Edit Account");
        System.out.println("6\t Exit");


        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your choice:");

        int choice2 = in.nextInt();

        switch (choice2) {
            case 1:
                System.out.println("Create Account");
                acc.create_account();

                break;
            case 2:
                System.out.println("Deposit");

                break;
            case 3:
                try
                {
                    acc.withdraw(2);

                }
                catch (WithdrawLimitException e)
                {
                    System.out.println("Sorry. You have attempted to withdraw " + e.showbalance() + "$ more than you have.");
                }
                break;
            case 4:
                //Call method show_balance from Account class
                acc.show_balance();
                break;
            case 5:
                System.out.println("Edit Account");
                acc.edit_account();
                break;
            case 6:
                acc.exit();;
                System.out.println("Exit");
                //Call method exit from Account class
                break;
            default:
                System.out.println("Invalid choice.");

                //End of banking main menu


        }
    }
}
