package com.expression.evaluator.operator;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class And implements Operator<Boolean, Boolean>{

    @Override
    public boolean execute(Boolean left, Boolean right) {
        return (left && right);
    }
}
