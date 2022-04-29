package org.example.titanic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.example.titanic.Utils.parseStringToDouble;
import static org.example.titanic.Utils.returnPrimitiveFromDouble;
import static org.example.titanic.Utils.roundNumber;

public class LogicServiceWithoutStream implements CalculateService{
    @Override
    public double getAverageAgeFromWomen(final List<Titanic> titanicList) {
        if (titanicList == null || titanicList.isEmpty()) {
            return 0.0;
        }

        double averageAge = 0;
        int totalCount = 0;

        for (Titanic titanic : titanicList) {
            if (titanic.hasWoman() && titanic.hasSurvied()) {
                averageAge += returnPrimitiveFromDouble(parseStringToDouble(titanic.getAge()));
                totalCount += 1;
            }
        }
        return roundNumber(averageAge / totalCount);
    }

    @Override
    public double getAverageAgeFromDrownedMen(final List<Titanic> titanicList) {
        if (titanicList == null || titanicList.isEmpty()) {
            return 0.0;
        }

        double averageAge = 0;
        int totalCount = 0;

        for (Titanic titanic : titanicList) {
            if (titanic.hasMan() && !titanic.hasSurvied()) {
                averageAge += returnPrimitiveFromDouble(parseStringToDouble(titanic.getAge()));
                totalCount += 1;
            }
        }
        return roundNumber(averageAge / totalCount);
    }

    @Override
    public Map<Integer, Integer> getMapByNamesLength(final List<Titanic> titanicList) {
        if (titanicList == null || titanicList.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (Titanic titanic : titanicList) {
            map.putIfAbsent(titanic.getName().length(), 0);
            map.computeIfPresent(titanic.getName().length(),  (key, val) -> val + 1);
        }
        return map;
    }
}
