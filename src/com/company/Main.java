package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();

    public static void readFile() {
        try {
            FileInputStream fileIn = new FileInputStream("users.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) in.readObject(); //ignore the error from explicit type
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("some class not found");
            c.printStackTrace();
        }
    }

    public static void writeFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("users.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static boolean inArrayList(String username) {
        boolean inarrayList = false;
        for (User user : users) {
            if (user.getName().equals(username)) {
                inarrayList = true;
                break;
            }
        }
        return inarrayList;
    }

    public static int getArrayListIndex(String username) {
        int ArrayListIndex = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(username)) {
                ArrayListIndex = i;
                break;
            }
        }
        return ArrayListIndex;
    }

    public static void main(String[] args) {
        //TODO: looping broken help :c


        Scanner userInput = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("choose action: ");

        if (userInput.hasNextLine()) {
            switch (userInput.nextLine()) {
                case "create":
                    users.add(new User());
                    break;
                case "select":
                    //TODO: hard, do later :p
                    break;

                case "statement":
                    users.
                    break;

                case "deposit":
                    break;

                case "withdraw":
                    break;

                case "request loan":
                    break;

                case "pay debt":
                    break;

                case "reset name":
                    break;

                case "reset password":
                    break;

                default:
                    System.out.println("invalid choice, help for choices");
                    if (userInput.nextLine().equals("help")) System.out.println(""); //TODO: add the choices;
                    break;
            }
        }
    }
}