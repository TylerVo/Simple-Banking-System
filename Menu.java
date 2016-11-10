package com.company;
import java.util.Scanner;

// Simple Banking System V 1.0 - Created by Madelin, Tyler, Kevin, and Ed.

public class Menu
{
    static Account acc;

    public static void main(String[] args) {

        //Create Objects
        acc = new Account();

        Scanner scan = new Scanner(System.in);

        /*All options in the menu for our app can be called by different methods.
          All the methods will be in one class called Account*/

        System.out.println("Welcome to the Simple Banking System.\n Please choose from the following options: ");

        Boolean fl = true;

        while (fl) {


            show_menu();

            System.out.println("Would you like to continue? Yes/No: ");

            String choice = scan.nextLine();
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

    public static void show_menu()
    {
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
                //Call method create_account from Account class
                //Ed implement this method
                break;
            case 2:
                System.out.println("Deposit");
                //Call method deposit from Account class
                //Kevin implement this method
                break;
            case 3:
                try
                {
                    acc.withdraw(2);
                    /*Call method withdraw from Account class
                    If user withdraws more than balance then throw
                    the custom exception */
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
                //Call method edit_account from Account class
                break;
            case 6:
                System.out.println("Exit");
                //Call method exit from Account class
                break;
            default:
                System.out.println("Invalid choice.");

                //End of banking main menu

        /*Optional: Use JavaFx class to make graphical user interface instead of text interface. At end
         of our development we may add Jframe and implement actionlistner interface. */

        }
    }
}
