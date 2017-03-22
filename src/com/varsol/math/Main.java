package com.varsol.math;

import com.varsol.math.controllers.NController;
import com.varsol.math.models.NNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by simon on 22.03.17.
 */
public class Main {

    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 4, 6, 3, 0));
        NNumber first = new NNumber(arr, 5);

        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(1, 4, 6, 3, 1));
        NNumber second = new NNumber(arr2, 5);

        System.out.print(NController.COM_NN_D(first, second));
    }

}
