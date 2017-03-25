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

    */

    public static NNumber MUL_Nk_N(NNumber natural, Integer k){
        NNumber res = new NNumber (natural.getNumbers(),k);
        for (int i = 0; i < k.intValue(); i++){
            res.getNumbers().add(0 , Integer.valueOf(0));
        }
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
        return first;
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

     Вычисление первой цифры деления большего натурального на меньшее, домноженное на 10^k,
        где k - номер позиции этой цифры (номер считается с нуля)
     На вход: Два натуральных длинных числа NNumber first, NNumber second; k - коэффициент
     Возвращает: Натуральное длинное число NNumber

     -------------------------------------------------------------------------------------------
     */

    public static NNumber DIV_NN_Dk(NNumber first, NNumber second) {

        NNumber arresult = DIV_NN_N(first, second);

        if (arresult != null) {
             for (int i = 0; i < arresult.getElderPosition() - 1; i++) {
                arresult.getNumbers().remove(0);
             }

            arresult = MUL_Nk_N(arresult, arresult.getElderPosition() - 1);

            //System.out.println(arresult.getNumbers());

            return  arresult;
        } else {
            return null;
        }
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

        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0));
        NNumber arresult = new NNumber(result, result.size());
        if (COM_NN_D(first, second) == 1) {
            NNumber pos = first;
            first = second;
            second = pos;
        }
        if (!NZER_N_B(second)) {
            do {
                first = SUB_NN_N(first, second);
                ADD_1N_N(arresult);
                System.out.println("ARRESULT = " + arresult.getNumbers());
            } while (COM_NN_D(first, second) != 1);
            arresult.setElderPosition(result.size());
            return  arresult;
        } else {
            return null;
        }
    }

    public static NNumber MOD_NN_N(NNumber first, NNumber second) {

        return null;
    }

    public static NNumber GCF_NN_N(ArrayList<NNumber> numbers){
        return null;
    }

    public static NNumber LCM_NN_N(ArrayList<NNumber> numbers) {
        return null;
    }
}
