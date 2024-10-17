package com.pluralsight;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(){
        this.date = this.date;
        this.time = this.time;
        this.description = this.description;
        this.vendor = this.vendor;
        this.amount = this.amount;
    }

    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public String getVendor(){
        return vendor;
    }
    public double getAmount(){
        return amount;
    }
}