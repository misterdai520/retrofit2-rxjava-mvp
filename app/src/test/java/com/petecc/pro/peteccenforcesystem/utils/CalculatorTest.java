package com.petecc.pro.peteccenforcesystem.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 作者：daiyf on 2017/3/31 11:23
 * 邮箱：misterdai@126.com
 */
public class CalculatorTest {
    private Calculator mCalculator;
    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void test1() throws Exception {
        //mCalculator.test();
    }

    @Test
    public void sum() throws Exception {
        assertEquals(6d, mCalculator.sum(1d, 5d), 0);
    }

    @Test
    public void substract() throws Exception {
        assertEquals(1d, mCalculator.substract(5d, 4d), 0);
    }

    @Test
    public void divide() throws Exception {
        assertEquals(4d, mCalculator.divide(20d, 5d), 0);
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(10d, mCalculator.multiply(2d, 5d), 0);
    }

}