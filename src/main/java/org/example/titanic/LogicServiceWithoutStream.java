package org.example.titanic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.example.titanic.utils.Utils.isMan;
import static org.example.titanic.utils.Utils.isSurvived;
import static org.example.titanic.utils.Utils.isWoman;
import static org.example.titanic.utils.Utils.roundNumber;
import org.example.titanic.model.RequestBean;
import org.example.titanic.model.Titanic;

public class LogicServiceWithoutStream implements CalculateService {
    @Override
    public double getAverageAgeFromWomen(final List<? extends RequestBean> titanicList) {
        if (titanicList == null || titanicList.isEmpty()) {
            return 0.0;
        }

        final List<Titanic> newList = (List<Titanic>) titanicList;
        double averageAge = 0;
        int totalCount = 0;

        for (Titanic obj : newList) {
            if (isWoman(obj) && isSurvived(obj)) {
                averageAge += obj.getAge();
                totalCount += 1;
            }
        }
        return roundNumber(averageAge / totalCount);
    }

    @Override
    public double getAverageAgeFromDrownedMen(final List<? extends RequestBean> titanicList) {
        if (titanicList == null || titanicList.isEmpty()) {
            return 0.0;
        }
        final List<Titanic> newList = (List<Titanic>) titanicList;

        double averageAge = 0;
        int totalCount = 0;

        for (Titanic obj : newList) {
            if (isMan(obj) && isSurvived(obj)) {
                averageAge += obj.getAge();
                totalCount += 1;
            }
        }
        return roundNumber(averageAge / totalCount);
    }

    @Override
    public Map<Integer, Integer> getMapByNamesLength(final List<? extends RequestBean> titanicList) {
        if (titanicList == null || titanicList.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();

        final List<Titanic> newList = (List<Titanic>) titanicList;
        for (Titanic titanic : newList) {
            map.putIfAbsent(titanic.getName().length(), 0);
            map.computeIfPresent(titanic.getName().length(), (key, val) -> val + 1);
        }
        return map;
    }
}
