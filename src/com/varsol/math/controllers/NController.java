package com.varsol.math.controllers;

import com.varsol.math.models.NNumber;

import java.util.ArrayList;

/**
 * Created by simon on 21.03.17.
 *
 *  Модуль работы с натуральными числами
 */
public class NController {

    public static Integer COM_NN_D(NNumber first, NNumber second) {
        if (first.getElderPosition() > second.getElderPosition()){
            return 2;
        } else if (first.getElderPosition() < second.getElderPosition()){
            return 1;
        } else {
            ArrayList<Integer> num1 = first.getNumbers();
            ArrayList<Integer> num2 = second.getNumbers();
            for (int i = first.getElderPosition() - 1; i >= 0; i--){
                if (num1.get(i) > num2.get(i)){
                    return 2;
                }

                if (num2.get(i) > num1.get(i)){
                    return 1;
                }
            }
            return 0;
        }
    }

    public static Boolean NZER_N_B(NNumber number){
        ArrayList<Integer> num = number.getNumbers();
        if (num.get(number.getElderPosition()) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static NNumber ADD_1N_N(NNumber number){
        return null;
    }

    public static NNumber ADD_NN_N(NNumber first, NNumber second){
        return null;
    }

    public static NNumber SUB_NN_N(NNumber first, NNumber second){
        return null;
    }

    public static NNumber MUL_ND_N(NNumber natural, Integer number){
        return null;
    }

    public static NNumber MUL_Nk_N(NNumber natural, Integer k){
        return null;
    }

    public static NNumber MUL_NN_N(NNumber first, NNumber second){
        return null;
    }

    public static NNumber SUB_NDN_N(NNumber first, NNumber second, Integer number){
        return null;
    }

    public static NNumber DIV_NN_Dk(NNumber first, NNumber second, Integer k){
        return null;
    }

    public static NNumber DIV_NN_N(NNumber first, NNumber second){
        return null;
    }

    public static NNumber MOD_NN_N(NNumber first, NNumber second){
        return null;
    }

    public static NNumber GCF_NN_N(ArrayList<NNumber> numbers){
        return null;
    }

    public static NNumber LCM_NN_N(ArrayList<NNumber> numbers) {
        return null;
    }
}
