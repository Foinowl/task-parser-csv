package org.example.titanic;

import java.util.List;
import java.util.Map;
import org.example.titanic.model.RequestBean;

public interface CalculateService {
    //    1. Подсчитать средний возраст выживших женщин.
    double getAverageAgeFromWomen(List<? extends RequestBean> titanicList);


    //2. Подсчитать средний возраст утонувших мужчин.
    double getAverageAgeFromDrownedMen(List<? extends RequestBean> titanicList);

    //    Получить HashMap, где ключ - количество символов в имени, а значение - количество таких имен
    Map<Integer, Integer> getMapByNamesLength(List<? extends RequestBean> titanicList);
}
