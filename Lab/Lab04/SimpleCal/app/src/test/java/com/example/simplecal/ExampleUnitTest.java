package com.example.simplecal;

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
   private Calculator mCalculator;

   // setting up the environment
    @Before
    public void setUp(){
        mCalculator = new Calculator();
    }

    // testing the addition
    @Test
    public void addTwoNumbers(){
        double resultAdd = mCalculator.addition(1d,1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

}