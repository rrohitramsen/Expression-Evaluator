package com.expression.evaluator.operator;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class GreaterThen<L extends Comparable,R extends Comparable> implements Operator<L,R>{

    @Override
    public boolean execute(L left, R right) {
        return (left.compareTo(right) > 0);
    }
}
