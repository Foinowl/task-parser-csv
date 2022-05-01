package org.example.titanic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import static org.example.titanic.utils.Utils.roundNumber;
import org.example.titanic.model.Passenger;
import org.example.titanic.service.AbstractBaseService;
import org.example.titanic.service.PassengerService;
import org.example.titanic.utils.Utils;

public class PassengerServiceWithoutStream extends AbstractBaseService<Passenger>
    implements PassengerService<Passenger> {
    @Override
    public double getAverageAgeFromWomen(final List<Passenger> lists) {

        return handleAverageValue(lists, Passenger::getAge,
            preparePredicateChainWithAnd(Utils::isWoman, Utils::isSurvived));
    }

    @Override
    public double getAverageAgeFromDrownedMen(final List<Passenger> lists) {

        return handleAverageValue(lists, Passenger::getAge,
            preparePredicateChainWithAnd(Utils::isMan, Utils::isSurvived));

    }

    @Override
    public Map<Integer, Integer> getMapByNamesLength(final List<Passenger> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (Passenger titanic : lists) {
            map.putIfAbsent(titanic.getName().length(), 0);
            map.computeIfPresent(titanic.getName().length(), (key, val) -> val + 1);
        }
        return map;
    }


    @Override
    public double handleAverageValue(final List<Passenger> lists, final Function<Passenger, Double> function,
                                     final Predicate<Passenger> predicates) {
        if (lists == null || lists.isEmpty()) {
            return 0.0;
        }

        double averageAge = 0;
        int totalCount = 0;

        for (Passenger obj : lists) {
            if (predicates.test(obj)) {
                averageAge += obj.getAge();
                totalCount += 1;
            }
        }
        return roundNumber(averageAge / totalCount);
    }
}
