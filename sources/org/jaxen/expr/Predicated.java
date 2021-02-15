package org.jaxen.expr;

import java.io.Serializable;
import java.util.List;

public interface Predicated extends Serializable {
    void addPredicate(Predicate predicate);

    PredicateSet getPredicateSet();

    List getPredicates();
}
