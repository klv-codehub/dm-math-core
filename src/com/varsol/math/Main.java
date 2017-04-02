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
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(6));
        NNumber first = new NNumber(arr, arr.size());

        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(4, 1));
        NNumber second = new NNumber(arr2, arr2.size());

        ArrayList<Integer> arr3 = new ArrayList<>(Arrays.asList(0, 1));
        NNumber third= new NNumber(arr3, arr3.size());

        ArrayList<Integer> arr4 = new ArrayList<>(Arrays.asList(6, 2));
        NNumber fourth = new NNumber(arr4, arr4.size());

        ArrayList<NNumber> arr5 = new ArrayList<>(Arrays.asList(first, second, third, fourth));

        System.out.println("Aaaaaaaa" + NController.GCF_NN_N(arr5).getNumbers());
        System.out.println("Aaaaaaaa" + NController.LCM_NN_N(arr5).getNumbers());

        //System.out.println(NController.ADD_NN_N(first, second).getNumbers());
        //System.out.println(NController.MUL_NN_N(first, second).getNumbers());
        //System.out.println(NController.DIV_NN_N(second, first).getNumbers());


        //System.out.println(NController.SUB_NDN_N(first, second, 3).getNumbers());
        //NController.SUB_NDN_N(first, second, 2);
        //System.out.println(NController.COM_NN_D(first, second));

    }

}
