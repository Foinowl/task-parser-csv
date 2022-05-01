package org.example.titanic.service;

import java.util.function.Predicate;

public interface BaseService<T> {

    Predicate<T> preparePredicateChainWithAnd(Predicate<T>... predicates);

    Predicate<T> preparePredicateChainWithOr(Predicate<T>... predicates);
}
