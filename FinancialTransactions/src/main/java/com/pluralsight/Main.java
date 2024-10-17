package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        try {


            Scanner Scan = new Scanner(System.in);
            boolean run = true;
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv");
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
                        //get date and time
                        LocalDateTime localDateTime = LocalDateTime.now();
                        LocalDate date = localDateTime.toLocalDate();
                        LocalTime time = localDateTime.toLocalTime();
                        //what the user needs to input
                        System.out.print("Enter Description: ");
                        String description = Scan.nextLine();
                        System.out.print("Enter vendor: ");
                        String vendor = Scan.nextLine();
                        System.out.print("Enter Deposit amount:");
                        double depositAmount = Scan.nextDouble();
                        Scan.nextLine();
                        if (depositAmount > 0) {
                            //formatting the transaction as a csv string
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                            String transactionCSV = String.format("%s|%s|%s|%s|%.2f",
                            date.format(dateFormatter),
                            time.format(timeFormatter),
                                    description,
                                    vendor,
                                    depositAmount);
                            //write to the csvFile
                            bufferedWriter.write(transactionCSV);
                            bufferedWriter.newLine();
                            bufferedWriter.flush(); // ensuring data is written to file
                            System.out.println("Deposited successfully. ");



                        }
                        System.out.println("Press enter to exit. ");
                        Scan.nextLine();
                        break;
                    case "2":
                        LocalDateTime localDateTime1 = LocalDateTime.now();
                        LocalDate date1 = localDateTime1.toLocalDate();
                        LocalTime time1 = localDateTime1.toLocalTime();

                        System.out.print("Enter Description: ");
                        String newdescription = Scan.nextLine();
                        System.out.print("Enter vendor: ");
                        String newvendor = Scan.nextLine();
                        System.out.print("Enter payment amount : ");
                        double paymentAmount = Scan.nextDouble();
                        Scan.nextLine();
                        if (paymentAmount > 0) {
                            DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            DateTimeFormatter timeFormatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");

                            String transactionCSV = String.format("%s|%s|%s|%s|%.2f",
                                    date1.format(dateFormatter1),
                                    time1.format(timeFormatter1),
                                    newdescription,
                                    newvendor,
                                    paymentAmount *= -1 );
                            //wrties to a negative amount
                            //write to the csvFile
                            bufferedWriter.write(transactionCSV);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();

                            System.out.println("Payment made successfully. ");
                        }
                        System.out.println("Press enter to exit. ");
                        Scan.nextLine();
                        break;
                    case "3":
                        System.out.println("Viewing Ledger ");
                        for (Transaction t : transactions) {
                            System.out.println(t);
                        }
                        System.out.println("Press enter to exit. ");
                        Scan.nextLine();
                        break;
                    case "4":
                        System.out.println("Goodbye ");
                        bufferedWriter.close();
                        Scan.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");


                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}





