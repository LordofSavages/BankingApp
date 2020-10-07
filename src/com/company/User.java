package com.company;

import java.util.Scanner;
import java.io.Serializable;

public class User implements Serializable {
    private String Name;
    private String Password;
    private double Interest;
    private Account Balance;
    private Account Debt;
    private Account Overdraft;


    public User() {
        // constructor method

        // Taking user input
        Scanner np = new Scanner(System.in);
        System.out.println("enter an account name: ");
        String name = np.nextLine();
        System.out.println("enter an account password: ");
        String pass = np.nextLine();
        np.close();

        this.Name = name;
        this.Password = pass;
        this.Interest = 20;
        this.Balance = new Account();
        this.Debt = new Account();
        this.Overdraft = new Account();
    }

    public boolean validatePassword() {
        Scanner pass = new Scanner(System.in);
        System.out.println("enter " + this.Name + "'s password: ");
        String pw = pass.nextLine();
        pass.close();

        if (pw.equals(this.Password)) {
            return true;
        } else return false;
    }

    public void printAll() {
        //TODO: might not concatenate string and double
        if (validatePassword()) {
            System.out.println(
                    this.Name +
                    "\nInterest: " + this.Interest +
                    "\nBalance: " + this.Balance.get() +
                    "\nDebt: " + this.Debt.get() +
                    "\nOverdraft: " + this.Overdraft.get()
            );
        }
    }

    public void setName() {
        if (validatePassword()) {
            String old_name = this.Name;

            Scanner name = new Scanner(System.in);
            System.out.println("enter new username: ");
            String _name = name.nextLine();
            name.close();

            this.Name = _name;
            System.out.println(old_name + " is now " + this.Name);
        }
    }

    public void setPassword() {
        if (validatePassword()) {
            Scanner pw = new Scanner(System.in);
            System.out.println("enter new password");
            String pass = pw.nextLine();
            pw.close();

            this.Password = pass;
            System.out.println("password successfully changed");
        }
    }

    public String getName() {
        return this.Name;
    }

    public void Deposit() {
        if (validatePassword()) {
            double amount;

            do {
                Scanner dep = new Scanner(System.in);
                System.out.println("amount to deposit: ");
                amount = dep.nextDouble();
                if (amount <= 0) {
                    System.out.println("enter a valid amount or cancel");
                    String cancel = dep.nextLine();
                    if (cancel.equals("cancel")) break;
                }
            } while (amount <= 0);

            this.Balance.increase(amount);
        }
    }

    public void Withdraw() {
        if (validatePassword()) {
            double amount;

            do {
                Scanner wit = new Scanner(System.in);
                System.out.println("amount to withdraw: ");
                amount = wit.nextDouble();
                if (amount <= 0) {
                    System.out.println("enter a valid amount or cancel");
                    String cancel = wit.nextLine();
                    if (cancel.equals("cancel")) break;
                }
            } while (amount <= 0);

            double remainder = this.Balance.decrease(amount);
            if (remainder > 0) this.Overdraft.increase(remainder);
        }
    }

    public void requestLoan() {
        if (validatePassword()) {
            double amount;

            do {
                Scanner wit = new Scanner(System.in);
                System.out.println("amount to withdraw: ");
                amount = wit.nextDouble();
                if (amount <= 0 || amount > (2 * this.Balance.get())) {
                    if (amount <= 0) {
                        System.out.println("enter a valid amount or cancel");
                        String cancel = wit.nextLine();
                        if (cancel.equals("cancel")) break;
                    } else {
                        String yn;
                        System.out.println(
                            "can't loan this amount,\n" +
                            "would you like to request the maximum of:\n" +
                            (2 * this.Balance.get())
                        );

                        // TODO: uh idk wtf im doing lol
                        while (true) {
                            yn = wit.nextLine();
                            if (yn.equals("yes")) {
                                amount = 2 * this.Balance.get();
                                System.out.println("loan of " + amount + " granted");
                                this.Debt.increase(amount);
                                this.Balance.increase(amount);
                                break;
                            } else if(yn.equals("no") || yn.equals("cancel")) {
                                break;
                            } else {
                                System.out.println("enter a valid operation");
                            }
                        }
                        // this sir right here
                    }
                }
            } while (amount <= 0 || amount > (2 * this.Balance.get()));
        }
    }

    public void payDebt() {
        // TODO: implement paying debt with interest and reduction in interest

        if (validatePassword()) {
            double amount;

            do {
                Scanner pay = new Scanner(System.in);
                System.out.println("amount to pay: ");
                amount = pay.nextDouble();
                if (amount <= 0 || amount > this.Debt.get()) {
                    System.out.println("enter a valid amount or cancel");
                    String cancel = pay.nextLine();
                    if (cancel.equals("cancel")) break;
                }
            } while (amount <= 0 || amount > this.Debt.get());

            // implementing the interest
            double p_amount = ((100 + this.Interest) / 100) * amount;

            double remainder = this.Balance.decrease(p_amount);
            if (remainder > 0) this.Overdraft.increase(remainder);
            this.Debt.decrease(amount);

            // reducing interest when loans paid
            this.Interest -= amount / 1000;
        }
    }
}