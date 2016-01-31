package com.ashish.rest;

import java.sql.Date;

/**
 * Created by admin on 31-Jan-16.
 */
public class Credit {
    private Date date;
    private int amount;
    private String person;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Credit() {
    }


    @Override
    public String toString() {
        return "Credit{" +
                "date=" + date +
                ", amount=" + amount +
                ", person='" + person + '\'' +
                '}';
    }

    public Credit(int amount, String person, Date date ) {
        this.date = date;
        this.amount = amount;
        this.person = person;
    }
}
