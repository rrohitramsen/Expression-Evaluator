package com.expression.evaluator.operator;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public enum OperatorNames {
    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    EQUALS("EQ"),
    GREATER_THEN("GT"),
    LESS_THEN("LT"),
    CONTAINS("IN");

    private String name;

    OperatorNames(String name) {
        this.name = name;
    }

    /**
     *
     * @param name
     * @return
     */
    public static boolean isOperator(String name) {
        for (OperatorNames operator : OperatorNames.values()) {

            if (operator.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param name
     * @return
     */
    public static OperatorNames getOperator(String name) {

        for (OperatorNames operator : OperatorNames.values()) {
            if (operator.name.equals(name)) {
                return operator;
            }
        }
        return null;
    }
}
