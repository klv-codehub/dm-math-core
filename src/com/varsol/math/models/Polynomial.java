package com.varsol.math.models;

import java.util.ArrayList;

/**
 * Created by simon on 21.03.17.
 *
 * Многочлен
 */
public class Polynomial {

    //Коэфициенты
    private ArrayList<QNumber> coefficient;

    //Степень
    private Integer power;

    public Polynomial(ArrayList<QNumber> coefficient, Integer power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public ArrayList<QNumber> getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(ArrayList<QNumber> coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
