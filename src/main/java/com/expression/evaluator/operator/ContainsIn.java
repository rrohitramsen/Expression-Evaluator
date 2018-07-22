package com.expression.evaluator.operator;

import java.util.List;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class ContainsIn<L, R extends List> implements Operator<L,R> {

    /**
     * TODO need to parse the domain values out of the expression
     * @param left operand
     * @param right operand or value
     * @return
     */
    @Override
    public boolean execute(L left, R right) {
        return right.contains(left);
    }
}
