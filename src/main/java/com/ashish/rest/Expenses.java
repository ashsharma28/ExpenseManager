package com.ashish.rest;

import java.sql.Date;

/**
 * Created by admin on 28-Jan-16.
 */
public class Expenses {

    private int amount =100;
    private String  reason = "somewhere";
    private Date date = null;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Expenses(int amount, String reason, Date date) {
        this.amount = amount;
        this.date = date;
        this.reason = reason;
    }

    public static void main(String[] args) {

        long dateTime = new java.util.Date().getTime() ;
        java.sql.Date date1 = new java.sql.Date(dateTime);

    }

    public Expenses() {

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    @Override
    public String toString() {
        return "Expenses{" +
                "amount=" + amount +
                ", reason='" + reason + '\'' +
                '}';
    }
}
