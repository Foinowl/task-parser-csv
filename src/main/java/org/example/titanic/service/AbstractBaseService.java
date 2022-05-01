package org.example.titanic.service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class AbstractBaseService<T1> implements BaseService<T1> {

    @Override
    public Predicate<T1> preparePredicateChainWithAnd(final Predicate<T1>... predicates) {
        Predicate<T1> p = Stream.of(predicates).reduce(x -> true, Predicate::and);
        return p;
    }

    @Override
    public Predicate<T1> preparePredicateChainWithOr(final Predicate<T1>... predicates) {
        return Stream.of(predicates).reduce(x -> true, Predicate::or);
    }

    public abstract double handleAverageValue(List<T1> lists, Function<T1, Double> function, Predicate<T1> predicates);
}
