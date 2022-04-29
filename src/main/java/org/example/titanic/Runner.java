package org.example.titanic;

import java.util.List;

public class Runner {

    public static void main(String[] args) {


        CalculateService calculateService = new LogicServiceWithStream();

        List<Titanic> lists = Utils.getListTitanic();

        System.out.println("1. Подсчитать средний возраст выживших женщин:");
        System.out.println(calculateService.getAverageAgeFromWomen(lists));

        System.out.println("2. Подсчитать средний возраст утонувших мужчин:");
        System.out.println(calculateService.getAverageAgeFromDrownedMen(lists));

        System.out.println(
            "3. Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен:");
        System.out.println(calculateService.getMapByNamesLength(lists));

    }

}
