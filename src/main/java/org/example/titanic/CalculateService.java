package org.example.titanic;

import java.util.List;
import java.util.Map;

public interface CalculateService {
    //    1. Подсчитать средний возраст выживших женщин.
    double getAverageAgeFromWomen(List<Titanic> titanicList);


    //2. Подсчитать средний возраст утонувших мужчин.
    double getAverageAgeFromDrownedMen(List<Titanic> titanicList);

    //    Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен
    Map<Integer, Integer> getMapByNamesLength(List<Titanic> titanicList);


    List<Titanic> getListTitanic();
}
