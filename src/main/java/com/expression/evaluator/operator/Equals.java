package com.expression.evaluator.operator;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class Equals<L,R> implements Operator<L,R>{

    @Override
    public boolean execute(L left, R right) {
        return left.equals(right);
    }

}
