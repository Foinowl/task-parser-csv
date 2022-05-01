package org.example.titanic.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static org.example.titanic.utils.Utils.roundNumber;
import org.example.titanic.model.Passenger;
import org.example.titanic.service.AbstractBaseService;
import org.example.titanic.service.PassengerService;
import org.example.titanic.utils.Utils;


public class PassengerServiceWithStream extends AbstractBaseService<Passenger>
    implements PassengerService<Passenger> {

    @Override
    public double getAverageAgeFromWomen(final List<Passenger> lists) {


        Function<Passenger, Double> fun = Passenger::getAge;
        return handleAverageValue(lists,
            Passenger::getAge,
            preparePredicateChainWithAnd(Utils::isWoman, Utils::isSurvived));
    }


    @Override
    public double getAverageAgeFromDrownedMen(final List<Passenger> lists) {

        return handleAverageValue(lists, Passenger::getAge, preparePredicateChainWithAnd(Utils::isMan, Utils::isSurvived));

    }

    @Override
    public Map<Integer, Integer> getMapByNamesLength(final List<Passenger> lists) {

        Map<Integer, Integer> objs =
            lists.stream().collect(Collectors.toMap(obj -> obj.getName().length(), obj1 -> 1, Integer::sum));


//       Ключ - кол-во символов в слове, Значение - коллекция всех таких слов
        HashMap<Integer, ArrayList<String>> objs1 = getMap(lists.stream()
                .map(object -> Objects.toString(object.getName(), null))
                .collect(Collectors.toList()),
            String::length, HashMap::new, ArrayList::new);

        return objs;
    }

    private <K, V, C extends Collection<V>, M extends Map<K, C>> M getMap(List<V> list,
                                                                          Function<? super V, ? extends K> classifier,
                                                                          Supplier<M> mapSupplier,
                                                                          Supplier<C> collectionSupplier) {
        return list.stream().collect(
            Collectors.groupingBy(classifier, mapSupplier, Collectors.toCollection(collectionSupplier))
        );
    }

    @Override
    protected double handleAverageValue(final List<Passenger> lists, final Function<Passenger, Double> function,
                                     final Predicate<Passenger> predicates
    ) {
        Double value = lists
            .stream()
            .filter(predicates)
            .mapToDouble(function::apply)
            .average()
            .orElse(Double.NaN);

        return roundNumber(value);
    }
}
