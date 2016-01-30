package com.ashish.rest;
/**
 * Created by admin on 28-Jan-16.
 */
public class Model {

    private int amount =100;
    private String  reason = "somewhere";

    public Model(int amount, String reason) {
        this.amount = amount;
        this.reason = reason;
    }

    public Model() {

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
        return "Model{" +
                "amount=" + amount +
                ", reason='" + reason + '\'' +
                '}';
    }
}
