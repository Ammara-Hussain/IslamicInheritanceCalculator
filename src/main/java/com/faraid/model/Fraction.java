//Ensures exact Quranic shares without decimal rounding errors.
package com.faraid.model;

public class Fraction {
    private final long numerator;
    private final long denominator;

    public Fraction(long numerator, long denominator) {
        if (denominator == 0) throw new ArithmeticException("Division by zero");
        long common = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / common;
        this.denominator = denominator / common;
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public Fraction add(Fraction other) {
        return new Fraction(this.numerator * other.denominator + other.numerator * this.denominator, this.denominator * other.denominator);
    }

    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public long getNumerator() { return numerator; }
    public long getDenominator() { return denominator; }

    @Override
    public String toString() { return numerator + "/" + denominator; }
}