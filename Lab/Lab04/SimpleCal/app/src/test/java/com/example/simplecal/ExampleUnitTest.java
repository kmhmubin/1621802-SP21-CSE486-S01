package com.example.simplecal;

import com.google.android.material.textfield.TextInputLayout;

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

    private Calculator mCalculator;

    // setting up the environment
    @Before
    public void setUp() {
        mCalculator = new Calculator();
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

    // subtraction test positive value
    @Test
    public void subPositive_isCorrect(){
        assertEquals(0d,1d,1d);
    }
    // subtraction test negative value
    @Test
    public void subNegative_isCorrect(){
        assertEquals(-16d,1d,17d);
    }

    // test for simple division
    @Test
    public void division_isCorrect(){
        assertEquals(16d,2d,32d);
    }

    // test for multiplication
    @Test
    public void multiply_isCorrect(){
        double resultMul = mCalculator.multiply(32d,2d);
        assertThat(resultMul,is(equalTo(64d)));
    }

    // Test for divide by zero
    @Test
    public void divByZeroThrows(){
        double resultDiv = mCalculator.division(16d,0d);
        assertThat(resultDiv,is(equalTo(Double.POSITIVE_INFINITY)));
    }

}