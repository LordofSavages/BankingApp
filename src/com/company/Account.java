package com.company;

public class Account {
    private double Value;

    public Account() {
        Value = 0;
    }

    public double get() {
        return this.Value;
    }

    public void increase(double amount) {
        this.Value += amount;
    }
    public double decrease(double amount) {
        double remainder;
        if (amount > this.Value) {
            remainder = amount - this.Value;
        } else remainder = 0;
        this.Value -= amount - remainder;
        return remainder;
    }
}
