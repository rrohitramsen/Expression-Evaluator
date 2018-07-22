package com.expression.evaluator.operator;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public interface Operator<L, R> {

    /**
     *
     * @param left operand
     * @param right operand or value
     * @return true or false
     */
    boolean execute(L left, R right);
}
