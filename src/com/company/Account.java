package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Account {


    private double tot;
    private String n;
    private double intr;
    private double time;
    private String data;
    private List<String> items;


    public void readFile() {
        try {
            File myObj = new File("prospects.txt");
            Scanner myReader = new Scanner(myObj);
            boolean ignoreFirst = false;
            int i = 1;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                if (data.length() == 0 || data.length() == 1) {
                    continue;
                }
                if (ignoreFirst == true) {
                    items = Arrays.asList(data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
                    n = items.get(0).replaceAll("[\"]", "");
                    n = n.replaceAll(",", " ");
                    tot = Double.valueOf(items.get(1));
                    intr = Double.valueOf(items.get(2));
                    time = Double.valueOf(items.get(3));
                    System.out.println("Prospect " + i + ": " + n + " wants to borrow " + tot + "€ for a period of " + time + " years and pay " + fixedMonthlyPay(tot, intr, time) + " each month");
                    i++;
                }
                ignoreFirst = true;

            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public double computePow(double num, double exp) {
        double res = 1;
        for (int i = 0; i < exp; i++) {
            res *= num;
        }
        return res;

    }

    public double fixedMonthlyPay(double total, double interest, double time) {
        interest = interest / 100 / 12; //Interest to % -> / 100. To monthly interest / 12
        time *= 12; //Time to months -> * 12
        double d = total * (interest * computePow((1 + interest), time)) / (computePow((1 + interest), time) - 1);
        return d;
    }
}