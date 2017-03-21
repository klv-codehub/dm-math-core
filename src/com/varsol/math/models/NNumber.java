package com.varsol.math.models;

import java.util.ArrayList;

/**
 * Created by simon on 21.03.17.
 *
 * Натуральное число
 */
public class NNumber {

    //Цифры
    private ArrayList<Integer> numbers;

    //Старшая позиция(кол-во цифр)
    private Integer elderPosition;

    public NNumber(ArrayList<Integer> numbers, Integer elderPosition) {
        this.numbers = numbers;
        this.elderPosition = elderPosition;
    }

    public ArrayList getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList numbers) {
        this.numbers = numbers;
    }

    public Integer getElderPosition() {
        return elderPosition;
    }

    public void setElderPosition(Integer elderPosition) {
        this.elderPosition = elderPosition;
    }
}
