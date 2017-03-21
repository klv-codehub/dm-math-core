package com.varsol.math.models;

/**
 * Created by simon on 21.03.17.
 *
 * Рациональное число
 */
public class RNumber {

    //Числитель
    private ZNumber numerator;
    //Знаменатель
    private NNumber denominator;

    public RNumber(ZNumber numerator, NNumber denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public ZNumber getNumerator() {
        return numerator;
    }

    public void setNumerator(ZNumber numerator) {
        this.numerator = numerator;
    }

    public NNumber getDenominator() {
        return denominator;
    }

    public void setDenominator(NNumber denominator) {
        this.denominator = denominator;
    }
}
