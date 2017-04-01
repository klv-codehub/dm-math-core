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
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2, 2, 2));
        NNumber first = new NNumber(arr, 3);

        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(1, 1, 1));
        NNumber second = new NNumber(arr2, 3);

        //System.out.println(NController.DIV_NN_Dk(second, first).getNumbers());
        System.out.println(NController.ADD_NN_N(first, second).getNumbers());
        System.out.println(NController.MUL_NN_N(first, second).getNumbers());
        //System.out.println(NController.DIV_NN_N(second, first).getNumbers());


        //System.out.println(NController.SUB_NDN_N(first, second, 3).getNumbers());
        //NController.SUB_NDN_N(first, second, 2);
        //System.out.println(NController.COM_NN_D(first, second));

    }

}
