package com.solvd.university.models;

public class Fee {
    private int feeID;
    private int programID;
    private double amount;
    public Fee(){}

    public Fee(int feeID, int programID, double amount) {
        this.feeID = feeID;
        this.programID = programID;
        this.amount = amount;
    }

    public int getFeeID() {
        return feeID;
    }

    public void setFeeID(int feeID) {
        this.feeID = feeID;
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}


