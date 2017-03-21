package com.varsol.math.models;

/**
 * Created by simon on 21.03.17.
 *
 * Целое число
 */
public class ZNumber {

    //Натуральное
    private NNumber number;

    //Знак
    private Integer sign;

    public ZNumber(NNumber number, Integer sign) {
        this.number = number;
        this.sign = sign;
    }

    public NNumber getNumber() {
        return number;
    }

    public void setNumber(NNumber number) {
        this.number = number;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }
}
