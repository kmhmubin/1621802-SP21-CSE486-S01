package com.example.simplecal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {

    // setting up the environment
    @Before
    public void setUp() {
        Calculator mCalculator = new Calculator();
    }

    // testing the addition
    @Test
    public void additionPositive_isCorrect() {
        assertEquals(2d, 1d, 1d);
    }

    // addition with negative values
    @Test
    public void additionNegative_isCorrect() {
        assertEquals(1d, -1d, 2d);
    }

    @Test
    public  void additionFloating_isCorrect(){
        assertEquals(2.222,1.111d,1.111d);
    }

}