package org.example.titanic.service;

import java.util.List;
import java.util.Map;

public interface PassengerService<T> {
    //    1. Подсчитать средний возраст выживших женщин.
    double getAverageAgeFromWomen(List<T> titanicList);

    //2. Подсчитать средний возраст утонувших мужчин.
    double getAverageAgeFromDrownedMen(List<T> titanicList);

    //    Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен
    Map<Integer, Integer> getMapByNamesLength(List<T> titanicList);
}
