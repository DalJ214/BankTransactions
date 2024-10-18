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
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);//appending here will ensure that the file will not be overwritten when running it again but instead add to the existing one
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
                                    paymentAmount *= -1);
                            //is shown as a negative amount in the csv file
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
                        System.out.println("1. All entries ");
                        System.out.println("2. Display Deposits ");
                        System.out.println("3. Display Payments ");
                        System.out.println("4. Display Reports");
                        String selection2 = Scan.nextLine().trim().toLowerCase();


                        if (selection2.equals("1")) {
                            //the csv file is being read here
                            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
                            String line;
                            System.out.println("All Entries: ");
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }

                        } else if (selection2.equals("2")) {
                            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
                            String line;
                            System.out.println("All Deposits: ");
                            while ((line = reader.readLine()) != null) {
                                String[] parts = line.split("\\|");
                                if (parts.length == 5) { //the amount of parts
                                    double amount = Double.parseDouble(parts[4]); //Amount is the 5th part
                                    if (amount > 0) { //this will check if it is a positive value
                                        System.out.println(line);
                                    }
                                }
                            }

                        } else if (selection2.equals("3")) {
                            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
                            String line;
                            System.out.println("All Payments: ");
                            while ((line = reader.readLine()) != null) {
                                String[] parts = line.split("\\|");
                                if (parts.length == 5) {
                                    double amount = Double.parseDouble(parts[4]);
                                    if (amount < 0) { //this will check if it is a negative value
                                        System.out.println(line);

                                    }
                                }
                            }
                            reader.close();
                            //This will generate the report screen
                        }else if (selection2.equals("4")){
                            while (true){
                                System.out.println("Please choose an option below: ");
                                System.out.println("1. Month to Date ");
                                System.out.println("2. Previous Month ");
                                System.out.println("3. Year to Date ");
                                System.out.println("4. Previous Year ");
                                System.out.println("5. Search by Vendor ");
                                System.out.println("6. Return to ledger menu");

                                String Option = Scan.nextLine().trim();
                                if (Option.equals("6")){
                                    break;
                                }
                                //I will use another switch case to create each option presented in reports option
                                switch (Option){
                                    case "1" :  //This case represents months to date in the reports option
                                        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
                                        String line;
                                        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
                                        LocalDate endDate = LocalDate.now();
                                        System.out.println("Transactions from " + startDate + "to " + endDate + ":");
                                        while ((line = reader.readLine()) != null ){
                                            String[] parts = line.split("\\|");
                                            if (parts.length == 5);
                                            LocalDate date2 = LocalDate.parse(parts[0]);
                                            if (date2.isEqual(startDate) || date2.isAfter(startDate)
                                                    &&
                                                    (date2.isEqual(endDate) || date2.isBefore(endDate))){
                                                System.out.println(line);
                                            } break;
                                        } case "2": // this case previous months in the reports option
                                            BufferedReader reader1 = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
                                            LocalDate previousMonthGenesis = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                                            LocalDate previousMonthend = LocalDate.now().minusMonths(1).withDayOfMonth(previousMonthGenesis.lengthOfMonth());
                                            System.out.println("Transactions from " + previousMonthGenesis + "to" + previousMonthend + ":");
                                            while ((line = reader1.readLine()) != null){
                                                String[] parts = line.split("\\|");
                                                if (parts.length == 5 ) {
                                                    LocalDate date2 = LocalDate.parse(parts[0]);
                                                    if ((date2.isEqual(previousMonthGenesis) || date2.isAfter(previousMonthGenesis) &&
                                                            date2.isEqual(previousMonthend) || date2.isBefore(previousMonthend))){
                                                        System.out.println(line);
                                                    }break;
                                                }
                                            } case "3": //this case displays year to date in the reports option
                                        BufferedReader reader2 = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
                                        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
                                        LocalDate endDate2 = LocalDate.now();
                                        System.out.println("Transaction from " + startOfYear + "to" + endDate2 + ":");
                                        while ((line = reader2.readLine()) != null){
                                            String[] parts = line.split("\\|");
                                            if (parts.length == 5) {
                                                LocalDate date2 = LocalDate.parse(parts[0]);
                                                if ((date2.isEqual(startOfYear) || date2.isAfter(startOfYear) &&
                                                        date2.isEqual(endDate2) || date2.isBefore(endDate2))){
                                                    System.out.println(line);
                                                }break;
                                        }
                                } case "4": //this case represents all previous years in the reports option
                                        BufferedReader reader3 = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
                                        LocalDate previousyear1 = LocalDate.now().minusYears(1).withDayOfYear(1);
                                        LocalDate previousyear2 = LocalDate.now().minusYears(1).withDayOfYear(previousyear1.lengthOfYear());
                                        System.out.println("Transactions from " + previousyear1 + "to " + previousyear2 + ":");
                                        while ((line = reader3.readLine()) != null) {
                                            String[] parts = line.split("\\|");
                                            if (parts.length == 5){
                                                LocalDate date2 = LocalDate.parse(parts[0]);
                                                if ((date2.isEqual(previousyear1) || date2.isAfter(previousyear1) &&
                                                        date2.isEqual(previousyear2) || date2.isBefore(previousyear2))){
                                                    System.out.println(line);
                                                }break;
                                            }
                                        } case "5": //this is the search by vendor code in the reports option
                                        System.out.println("Enter vendor name: ");
                                        String Vendor = Scan.nextLine();
                                            BufferedReader reader4 = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
                                            System.out.println("Transactions for vendor: " + Vendor );
                                            while ((line = reader4.readLine()) != null){
                                                String[] parts = line.split("\\|");
                                                if (parts.length == 5 && parts[3].equalsIgnoreCase(Vendor)){
                                                    System.out.println(line);
                                                }
                                            }


                            }
                        }
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



                        } catch(IOException e){
                        e.printStackTrace();
                    }


                }
            }











