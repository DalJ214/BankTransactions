package com.pluralsight;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try {


            Scanner Scan = new Scanner(System.in);
            boolean run = true;
            FileWriter fileWriter = new FileWriter("transactions.csv"); //To write to file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            List<Transaction> transactions = new ArrayList<>(); // Array to store transactions

            while (true) {  //Home screen menu that require user input
                System.out.println("    Home Screen   ");
                System.out.println("1. Add Deposit ");
                System.out.println("2. Make Payment(Debit) ");
                System.out.println("3. Ledger ");
                System.out.println("4. Exit ");
                String selection = Scan.nextLine().trim().toLowerCase();


                switch (selection) {
                    case "1":
                        System.out.println("Adding Deposit ");
                        System.out.println("Enter Deposit details " );
                        String input = Scan.nextLine();

                        if (processTransaction(input, transactions, bufferedWriter)){
                            System.out.println("Deposited successfully");
                        }else{
                            System.out.println("Invalid input, Please try again");
                        }
                        break;
                    case "2":
                        System.out.println("Making a payment  ");
                        System.out.println("Enter transaction details ");
                        input = Scan.nextLine();

                        
                        // code block
                        break;
                    case "3":
                        System.out.println("Viewing Ledger ");
                        break;
                    case "4":
                        System.out.println("Exit ");
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        // code block
                }

            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
