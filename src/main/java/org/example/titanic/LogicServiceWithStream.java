package org.example.titanic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static org.example.titanic.utils.Utils.roundNumber;
import org.example.titanic.model.Gender;
import org.example.titanic.model.RequestBean;
import org.example.titanic.model.Titanic;

public class LogicServiceWithStream implements CalculateService {
    @Override
    public double getAverageAgeFromWomen(final List<? extends RequestBean> titanicList) {

        final List<Titanic> newList = (List<Titanic>) titanicList;
        double value = newList
            .stream()
            .filter(t -> t.getGender().isGenderEquals(Gender.FEMALE))
            .filter(t -> t.getSurvived().isSurvived())
            .mapToDouble(Titanic::getAge)
            .average()
            .orElse(Double.NaN);

        return roundNumber(value);
    }

    @Override
    public double getAverageAgeFromDrownedMen(final List<? extends RequestBean> titanicList) {

        final List<Titanic> newList = (List<Titanic>) titanicList;
        double value = newList
            .stream()
            .filter(t -> t.getGender().isGenderEquals(Gender.MALE))
            .filter(t -> !t.getSurvived().isSurvived())
            .mapToDouble(Titanic::getAge)
            .average()
            .orElse(Double.NaN);

        return roundNumber(value);    }

    @Override
    public Map<Integer, Integer> getMapByNamesLength(final List<? extends RequestBean> titanicList) {

        final List<Titanic> newList = (List<Titanic>) titanicList;
        Map<Integer, Integer> objs =
            newList.stream().collect(Collectors.toMap(obj -> obj.getName().length(), obj1 -> 1, Integer::sum));

        HashMap<Integer, ArrayList<String>> objs1 = getMap(newList.stream()
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
}
