package com.company;


import org.junit.Assert;

public class UITest {


    @org.junit.jupiter.api.Test
    void computePow_5_to_3() {
        final double expected = 125;
        final double actual = UI.computePow(5, 3);
        Assert.assertEquals(actual, expected, 0.001);
    }

    @org.junit.jupiter.api.Test
    void fixedMonthlyPay_1200() {
        final String expected = "10,512";
        final String actual = UI.fixedMonthlyPay(1200, 1, 10);
        expected.equals(actual);

    }
    @org.junit.jupiter.api.Test
    void fixedMonthlyPay_bigNum() {
        final String expected = "824999,92";
        final String actual = UI.fixedMonthlyPay(9999999, 99, 100);
        expected.equals(actual);
    }


}
