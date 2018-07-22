package com.expression.evaluator.tree;

import java.util.Objects;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class Operand {

    private Object value;

    public Operand(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operand operand = (Operand) o;
        return Objects.equals(value, operand.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
