package com.varsol.math.controllers;

import com.varsol.math.models.NNumber;

import java.util.ArrayList;
import java.util.Arrays;

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
        @author

        Проверка на ноль: если число не равно нулю, то «да» иначе «нет»
        На вход: Натуральное длинное число NNumber
        Возвращает: Логическое значение (true/false)

    */

    public static Boolean NZER_N_B(NNumber number){
        return (!(number.getNumbers().size() == 1 && ((Integer) number.getNumbers().get(0)).intValue()== 0));
    }


    /**
        -------------------------------------------------------------------------------------------

        @author Artem Kaloev/Alexander Shvalev

        Добавление 1 к натуральному числу
        На вход: Натуральное длинное число NNumber
        Возвращает: Натуральное длинное число NNumber

        -------------------------------------------------------------------------------------------
    */
    public static NNumber ADD_1N_N(NNumber number){

        //создаем массив цифр, из которых состоит число
        ArrayList<Integer> num = number.getNumbers();

        //прибавляем единицу, если найдется цифра, меньшая 9
        for (int i = 0; i < number.getElderPosition(); i++) {
            if (num.get(i) < 9) {
                Integer n = num.get(i);
                num.set(i, ++n);
                //если такая цифра нашлась, то зануляем предыдущие разряды, задаем новые цифры и возвращаем число
                for (int j = i - 1; j >= 0; j--) {
                    num.set(j, 0);
                }
                number.setNumbers(num);
                //System.out.print(num);        //отладка
                return number;
            }
        }

        //добавляем разряд числа
        num.add(number.getElderPosition(), 1);

        //System.out.print(num);        //отладка

        //зануляем предыдущие разряды
        for (int j = 0; j < number.getElderPosition(); j++) {
           num.set(j, 0);
        }

        //передаем цифры и их кол-во в number
        number.setNumbers(num);
        number.setElderPosition(num.size());

        //System.out.print(num);        //отладка

        return number;
    }


    /**
        -------------------------------------------------------------------------------------------

        @author Artem Kaloev/Alexander Shvalev

        Сложение натуральных чисел
        На вход: Два натуральных длинных числа NNumber first, NNumber second
        Возвращает: Натуральное длинное число NNumber

        -------------------------------------------------------------------------------------------
    */

    public static NNumber ADD_NN_N(NNumber first, NNumber second){

        //System.out.println(first.getNumbers() + " - first");
        //System.out.println(second.getNumbers() + "- second");

        //меняем переменные так, чтобы first >= second
        if (COM_NN_D(first, second) == 1) {

            //System.out.println("YAH");

            NNumber tmp = first;
            first = second;
            second = tmp;
        }

        //создаем массивы цифр, из которых состоят числа
        ArrayList<Integer> num1 = first.getNumbers();

        //System.out.println(num1+ " - firstnum");


        ArrayList<Integer> num2 = second.getNumbers();

        //System.out.println(num2+ " - scndnum");

        ArrayList<Integer> result = new ArrayList<>();

        //System.out.println(first.getElderPosition() + " - 1eld");
        //System.out.println(second.getElderPosition() + " - 2eld");

        //для удобства вычисления обнуляем элементы массива меньшего размера
        for (int j = second.getElderPosition(); j < first.getElderPosition(); j++) {
            num2.add(j, 0);
        }

//        System.out.print(num1);     System.out.print(num2);       //отладка

        //производим суммирование "столбиком"
        int i;
        int sum = 0;
        for (i = 0; i < first.getElderPosition(); i++) {
            sum += num1.get(i) + num2.get(i);
            if (sum <= 9) {
                result.add(i, sum);
                sum = 0;
            } else {
                result.add(i, sum - 10);
                sum = 1;
                if (i == first.getElderPosition() - 1) {
                    result.add(++i, sum);
                }
            }
        }

        //System.out.print(num1 + "+" + num2 + "=" + result);     //отладка

        //создаем результирующее длинное число rResult
        NNumber rResult = new NNumber(result, result.size());

        return rResult;
    }


    /**
     -------------------------------------------------------------------------------------------

     @author Artem Kaloev/Alexander Shvalev

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
        for (int j = second.getElderPosition(); j < first.getElderPosition(); j++) {
            num2.add(j, 0);
        }

        //System.out.print(num1);             System.out.print(num2);               //отладка
        //System.out.println(num1.size());      System.out.println(num2.size());        //отладка

        //производим вычитание "столбиком"
        int i;
        int dif = 0;
        for (i = 0; i < first.getElderPosition(); i++) {
            Integer n = num1.get(i);
            num1.set(i, n + dif);
            if (num1.get(i) < num2.get(i)) {
                n = num1.get(i);
                num1.set(i, n + 10);
                dif = -1;
            } else {
                dif = 0;
            }
            result.add(i, num1.get(i) - num2.get(i));
        }

        //System.out.print(result);         //отладка

        //удаляем лишние нули числа (которые стоят спереди)
        for (i = first.getElderPosition() - 1; result.get(i) == 0 && result.size() != 1; i--) {
            result.remove(i);
        }

        //System.out.print(result);         //отладка

        //создаем результирующее длинное число rResult
        NNumber rResult = new NNumber(result, result.size());

        return rResult;
    }


    /*
        @author yaroslavok
        @author ItNoN
        @author EgorKolyshkin

        Умножение натурального числа типа NNumber на цифру
        На вход: Натуральное число типа NNumber, натуральное число
        Возвращает: Натуральное число

    */

    public static NNumber MUL_ND_N(NNumber natural, Integer number) {
        Integer index = Integer.valueOf(0);
        ArrayList res = new ArrayList();

        for (Integer i = Integer.valueOf(0); i.intValue() < natural.getElderPosition().intValue(); i = Integer.valueOf(i.intValue() + 1)) {
            res.add(Integer.valueOf(number.intValue() * ((Integer) natural.getNumbers().get(i.intValue())).intValue() % 10 + index.intValue()));
            index = Integer.valueOf(number.intValue() * ((Integer) natural.getNumbers().get(i.intValue())).intValue() / 10);
        }

        if (index.intValue() != 0) {
            res.add(index);
        }
        return new NNumber(res, Integer.valueOf(res.size()));
    }

    /*
        @author yaroslavok
        @author ItNoN
        @author EgorKolyshkin

        Умножение натурального числа типа NNumber на 10^k
        На вход: Натуральное число типа NNumber, k - коэффициент
        Возвращает: Натуральное число


        Правка: Artem Kaloev/Alexander Shvalev
    */

    public static NNumber MUL_Nk_N(NNumber natural, Integer k){
        NNumber res = new NNumber (natural.getNumbers(), natural.getElderPosition() + k);
        for (int i = 0; i < k.intValue(); i++){
            res.getNumbers().add(0 , Integer.valueOf(0));
        }
        //res.setElderPosition();
        //System.out.println("jojo" + res.getElderPosition());

        //System.out.print(res.getNumbers());
        return res;
    }


    /**
     -------------------------------------------------------------------------------------------

     @author

     Умножение натуральных чисел
     На вход: Два натуральных длинных числа NNumber first, NNumber second
     Возвращает: Натуральное длинное число NNumber

     -------------------------------------------------------------------------------------------
     */

    public static NNumber MUL_NN_N(NNumber first, NNumber second) {
        /*
        if(COM_NN_D(first, second).intValue() == 2) {
            NNumber pos = first;
            first = second;
            second = pos;
        }

        Integer modulo = Integer.valueOf(0);
        ArrayList bigger = second.getNumbers();
        ArrayList smaller = first.getNumbers();

        for(Integer pos1 = Integer.valueOf(0); pos1.intValue() < smaller.size(); pos1 = Integer.valueOf(pos1.intValue() + 1)) {
            int var6 = ((Integer)bigger.get(pos1.intValue())).intValue();
        }

        first.setNumbers(bigger);
        */

        if (COM_NN_D(first, second) == 1) {
            NNumber tmp = first;
            first = second;
            second = tmp;
        }

        ArrayList<Integer> mulArray = new ArrayList<>(Arrays.asList(0));
        NNumber mul = new NNumber(mulArray, mulArray.size());

        NNumber newMul = mul;

        for (int i = 0; i < second.getElderPosition(); i++) {

            //System.out.println("ND_N " + MUL_ND_N(first, (Integer) second.getNumbers().get(i)).getNumbers());
            //System.out.println("ND_N " + MUL_ND_N(first, (Integer) second.getNumbers().get(i)).getElderPosition());

            //System.out.println("Nk_N " + MUL_Nk_N (MUL_ND_N(first, (Integer) second.getNumbers().get(i)), i).getNumbers());
            //System.out.println("Nk_N " + MUL_Nk_N (MUL_ND_N(first, (Integer) second.getNumbers().get(i)), i).getElderPosition());


            //System.out.println("mul " + mul.getNumbers());


            mulArray = ADD_NN_N (MUL_Nk_N (MUL_ND_N(first, (Integer) second.getNumbers().get(i)), i), mul).getNumbers();

            mul.setNumbers(mulArray);

            //System.out.println("mulArray" + mulArray);

            mul.setElderPosition(mulArray.size());

            //System.out.println(mul.getNumbers());

        }
        //System.out.println(mul.getNumbers());
        return mul;
    }


    /**
     -------------------------------------------------------------------------------------------

     @author Artem Kaloev/Alexander Shvalev

     Вычитание из натурального другого натурального, умноженного на цифру для случая с неотрицательным результатом
     На вход: Два натуральных длинных числа NNumber first, NNumber second; цифра  number, на которую умножается
     вычитаемое число
     Возвращает: Натуральное длинное число NNumber

     -------------------------------------------------------------------------------------------
     */

    public static NNumber SUB_NDN_N(NNumber first, NNumber second, Integer number) {

        if (COM_NN_D(first, second) == 1) {
            NNumber tmp = first;
            first = second;
            second = tmp;
        }
        second = MUL_ND_N(second, number);
        return SUB_NN_N(first, second);

    }


    /**
     -------------------------------------------------------------------------------------------

     @author Artem Kaloev/Alexander Shvalev

     Вычисление первой цифры деления большего натурального на меньшее, (домноженное на 10^k,
        где k - номер позиции этой цифры (номер считается с нуля))
     На вход: Два натуральных длинных числа NNumber first, NNumber second; k - коэффициент
     Возвращает: Натуральное длинное число NNumber

     -------------------------------------------------------------------------------------------
     */

    public static NNumber DIV_NN_Dk(NNumber first, NNumber second) {

        Integer k = 0;
/*
        if (COM_NN_D(first, second) == 1) {
            NNumber pos = first;
            first = second;
            second = pos;
        }
*/
        //Выбор количества разрядов из большего числа, чтобы можно было произвести первый шаг деления столбиком.
        ArrayList <Integer> arr = new ArrayList<>();
        for (int i = 0; i < second.getElderPosition(); i++) {
            arr.add(0, (Integer) first.getNumbers().get(first.getElderPosition() - i - 1));
            //System.out.println(arr);

        }
        //System.out.println(arr);


        NNumber del = new NNumber(arr, second.getElderPosition());
        if (COM_NN_D(del, second) == 1) {
            arr.add(0, (Integer) first.getNumbers().get(first.getElderPosition() - second.getElderPosition() - 1));
            del.setNumbers(arr);
            del.setElderPosition(del.getElderPosition() + 1);
        }
        //NNumber del = new NNumber(arr, second.getElderPosition());
        //System.out.println(del.getNumbers());
        //System.out.println(del.getElderPosition());

        //System.out.println(second.getNumbers());
        //System.out.println(second.getElderPosition());

        //System.out.println(COM_NN_D(del, second));
        //Деление

        int tmp = first.getElderPosition() - del.getElderPosition();

        while (COM_NN_D(del, second) != 1) {
            del = SUB_NN_N(del, second);
            k++;
            //System.out.println(del);
            //System.out.println(k);
            //break;

        }
        //System.out.println(first.getElderPosition());

  //      System.out.println(del.getElderPosition());


        ArrayList<Integer> res = new ArrayList<>(Arrays.asList(k));
        NNumber result = new NNumber(res, res.size());
        return MUL_Nk_N(result, tmp);

    }


    /**
     -------------------------------------------------------------------------------------------

     @author Artem Kaloev/Alexander Shvalev

     Частное от деления большего натурального числа на меньшее или равное натуральное с остатком
     (делитель отличен от нуля)
     На вход: Два натуральных длинных числа NNumber first, NNumber second
     Возвращает: Натуральное длинное число NNumber

     -------------------------------------------------------------------------------------------
     */

    public static NNumber DIV_NN_N(NNumber first, NNumber second) {

        if (COM_NN_D(first, second) == 1) {
            NNumber pos = first;
            first = second;
            second = pos;
        }

        ArrayList arr = first.getNumbers();
        NNumber nf = new NNumber(arr, arr.size());
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0));
        NNumber arresult = new NNumber(result, result.size());
        ArrayList<Integer> curArray = new ArrayList<>(Arrays.asList(0));
        NNumber current = new NNumber(curArray, curArray.size());

//Деление столбиком
        while (COM_NN_D(nf, second) != 1) {
            current = DIV_NN_Dk(nf, second);
            //System.out.print(current.getNumbers());
            //System.out.print(second.getNumbers());

            //System.out.print(MUL_NN_N(second, current).getNumbers());

            nf = SUB_NN_N(nf, MUL_NN_N(second, current));
//            System.out.print(nf.getNumbers());

            arresult = ADD_NN_N(arresult, current);
  //          System.out.print(arresult.getNumbers());
        }
        return arresult;
    }



    public static NNumber MOD_NN_N(NNumber first, NNumber second) {

        if (COM_NN_D(first, second) == 1) {
            NNumber pos = first;
            first = second;
            second = pos;
        }
        //System.out.println(SUB_NN_N(first, MUL_NN_N(DIV_NN_N(first, second), second)).getNumbers());
        return SUB_NN_N(first, MUL_NN_N(DIV_NN_N(first, second), second));
    }



    public static NNumber GCF(NNumber first, NNumber second) {

        while (NZER_N_B(first) && NZER_N_B(second)) {
            //MOD_NN_N(first, second);
            if (COM_NN_D(first, second) == 2) {
                first = MOD_NN_N(first, second);
                //System.out.println(first.getNumbers());
            } else {
                second =  MOD_NN_N(second, first);
                //System.out.println(second.getNumbers());
            }


        }
        return ADD_NN_N(first, second);
    }



    public static NNumber GCF_NN_N(ArrayList<NNumber> numbers){


        NNumber gcf = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            gcf = GCF(gcf, numbers. get(i));
        }

        return gcf;
    }



    public static NNumber LCM(NNumber first, NNumber second) {
        return DIV_NN_N(MUL_NN_N(first, second), GCF(first, second));
    }



    public static NNumber LCM_NN_N(ArrayList<NNumber> numbers) {


        NNumber lcm = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            lcm = LCM(lcm, numbers. get(i));
        }

        return lcm;
    }
}
