package com.example.ashwanigupta.sharethefare;

import java.util.ArrayList;

/**
 * Created by ashwani gupta on 13-02-2017.
 */

public class Bill {

    int bill_id;
    String description;
    float amount;
    Friend paidBy;
    public  ArrayList<Friend> didExpense;
    float expenseOnEach;

    public Bill(int id, String description, float amount,float expenseOnEach) {
        this.bill_id=id;
        this.description=description;
        this.amount = amount;
        this.expenseOnEach=expenseOnEach;
        didExpense=new ArrayList<>();
    }

    public int getBill_id() {
        return bill_id;
    }

    public String getDescription() {
        return description;
    }

    public float getAmount() {
        return amount;
    }

    public float getExpenseOnEach() {
        return expenseOnEach;
    }

    public void setExpenseOnEach(float expenseOnEach) {
        this.expenseOnEach = expenseOnEach;
    }

    public Friend getPaidBy() {
        return paidBy;
    }

    public ArrayList<Friend> getDidExpense() {
        return didExpense;
    }

    public void setPaidBy(Friend paidBy) {
        this.paidBy = paidBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
