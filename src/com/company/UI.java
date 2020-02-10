package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class UI {


    private double tot;
    private String n;
    private double intr;
    private double time;
    private String data;
    private List<String> items;

    public UI() {
        readFile();
    }

        public void readFile() {
            try {
                File myObj = new File("prospects.txt");
                Scanner myReader = new Scanner(myObj);
                boolean header = true;
                int i = 1;
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                        items = Arrays.asList(data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
                        if (items.size() != 4 || header) {
                            header = false;
                            continue;
                        }
                            n = items.get(0).replaceAll("[\"]", "");
                            n = n.replaceAll(",", " ");
                            tot = Double.valueOf(items.get(1));
                            intr = Double.valueOf(items.get(2));
                            time = Double.valueOf(items.get(3));
                            System.out.println(printAccs(i, n, tot, intr, time));
                            i++;
                }
                myReader.close();

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        public static double computePow(double num, double exp) {
            double res = 1;
            for (int i = 0; i < exp; i++) {
                res *= num;
            }
            return res;

        }

        public static String fixedMonthlyPay(double total, double interest, double time) {
            interest = interest / 100 / 12; //Interest to % -> / 100. To monthly interest / 12
            time *= 12; //Time to months -> * 12
            double d = total * (interest * computePow((1 + interest), time)) / (computePow((1 + interest), time) - 1);
            String s = roundNum(d);
            return s;
        }
        public String printAccs(int p, String n, double tot, double intr, double time) {
            return "Prospect " + p + ": " + n + " wants to borrow " + roundNum(tot) + "€ for a period of " + time + " years and pay " + fixedMonthlyPay(tot, intr, time) + "€ each month";
        }
        public static String roundNum(double num) {
            String s = String.format("%.2f", num);
            return s;



        }
    }