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


    /**
        @author klv-codehub

        Проверка на ноль: если число не равно нулю, то «да» иначе «нет»
        На вход: Натуральное длинное число NNumber
        Возвращает: Логическое значение (true/false)

    */

    public static Boolean NZER_N_B(NNumber number){
        ArrayList<Integer> num = number.getNumbers();
        if (num.get(0) == 0) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
        -------------------------------------------------------------------------------------------

        @author klv-codehub

        Добавление 1 к натуральному числу
        На вход: Натуральное длинное число NNumber
        Возвращает: Натуральное длинное число NNumber

        -------------------------------------------------------------------------------------------
    */
    public static NNumber ADD_1N_N(NNumber number){

        //создаем массив цифр, из которых состоит число
        ArrayList<Integer> num = number.getNumbers();

        //прибавляем единицу, если найдется цифра, меньшая 9
        for (int i = number.getElderPosition() - 1; i >= 0; i--) {
            if (num.get(i) < 9) {
                Integer n = num.get(i);
                num.set(i, ++n);
                //если такая цифра нашлась, то зануляем предыдущие разряды, задаем новые цифры и возвращаем число
                for (int j = i + 1; j < number.getElderPosition(); j++) {
                    num.set(j, 0);
                }
                number.setNumbers(num);
                //System.out.print(num);        отладка
                return number;
            }
        }

        //добавляем разряд числа
        int newElderPosition = number.getElderPosition() + 1;
        num.add(0, 1);

        //System.out.print(num);        отладка

        //зануляем предыдущие разряды
        for (int j = newElderPosition - 1; j > 0; j--) {
           num.set(j, 0);
        }

        //передаем цифры и их кол-во в number
        number.setNumbers(num);
        number.setElderPosition(newElderPosition);

        //System.out.print(num);        отладка

        return number;
    }


    /**
        -------------------------------------------------------------------------------------------

        @author klv-codehub

        Сложение натуральных чисел
        На вход: Два натуральных длинных числа NNumber first, NNumber second
        Возвращает: Натуральное длинное число NNumber

        -------------------------------------------------------------------------------------------
    */

    public static NNumber ADD_NN_N(NNumber first, NNumber second){

        //меняем переменные так, чтобы first >= second
        if (COM_NN_D(first, second) == 1) {
            NNumber tmp = first;
            first = second;
            second = tmp;
        }

        //создаем массивы цифр, из которых состоят числа
        ArrayList<Integer> num1 = first.getNumbers();
        ArrayList<Integer> num2 = second.getNumbers();
        ArrayList<Integer> result = new ArrayList<>();

        //для удобства вычисления обнуляем элементы массива меньшего размера
        for (int j = 0; j < first.getElderPosition() - second.getElderPosition(); j++) {
            num2.add(j, 0);
        }

        //System.out.print(num1);     System.out.print(num2);       отладка

        //производим суммирование "столбиком"
        int i;
        int sum = 0;
        for (i = first.getElderPosition() - 1; i >= 0; i--) {
            sum += num1.get(i) + num2.get(i);
            if (sum <= 9) {
                result.add(0, sum);
                sum = 0;
            } else {
                result.add(0, sum - 10);
                sum = 1;
                if (i == 0) {
                    result.add(0, sum);
                }
            }
        }

        //System.out.print(result);     отладка

        //создаем результирующее длинное число rResult
        NNumber rResult = new NNumber(result, i);

        return rResult;
    }


    /**
     -------------------------------------------------------------------------------------------

     @author klv-codehub

     Вычитание из первого большего натурального числа второго меньшего или равного
     На вход: Два натуральных длинных числа NNumber first, NNumber second
     Возвращает: Натуральное длинное число NNumber

     -------------------------------------------------------------------------------------------
     */

    public static NNumber SUB_NN_N(NNumber first, NNumber second){

        //меняем переменные так, чтобы first >= second
        if (COM_NN_D(first, second) == 1) {
            NNumber tmp = first;
            first = second;
            second = tmp;
        }

        //создаем массивы цифр, из которых состоят числа
        ArrayList<Integer> num1 = first.getNumbers();
        ArrayList<Integer> num2 = second.getNumbers();
        ArrayList<Integer> result = new ArrayList<>();

        //для удобства вычисления обнуляем элементы массива меньшего размера
        for (int j = 0; j < first.getElderPosition() - second.getElderPosition(); j++) {
            num2.add(j, 0);
        }

        //System.out.print(num1);             System.out.print(num2);               отладка
        //System.out.print(num1.size());      System.out.print(num2.size());        отладка

        //производим вычитание "столбиком"
        int i;
        int dif = 0;
        for (i = first.getElderPosition() - 1; i >= 0; i--) {
            Integer n = num1.get(i);
            num1.set(i, n + dif);
            if (num1.get(i) < num2.get(i)) {
                n = num1.get(i);
                num1.set(i, n + 10);
                dif = -1;
            } else {
                dif = 0;
            }
            result.add(0, num1.get(i) - num2.get(i));
        }
        System.out.println(result);


        //удаляем лишние нули числа (которые стоят спереди)
        while (result.get(0) == 0 && result.size() != 1) {
            result.remove(0);
            System.out.println(result);
        }
        int resultElderPosition = first.getElderPosition() - i;

        //System.out.print(result);         отладка

        //создаем результирующее длинное число rResult
        NNumber rResult = new NNumber(result, resultElderPosition);

        return rResult;
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
