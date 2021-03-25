package com.example.simplecal;

public class Calculator {
    // calculation operation
    public enum Operator {ADD, SUB, DIV, MUL}

    // Addition
    public double addition(double A, double B) {
        return A + B;
    }

    // Subtract
    public double subtraction(double A, double B) {
        return A - B;
    }

    // Divide
    public double division(double A, double B) {
        return A / B;
    }

    // Multiply
    public double multiply(double A, double B) {
        return A * B;
    }
}
