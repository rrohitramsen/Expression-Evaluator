package com.expression.evaluator.operator;

import com.expression.evaluator.api.EvaluatorExpressionException;

import java.util.Objects;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public abstract class OperatorFactory {

    /**
     *
     * @param operatorName
     * @return
     */
    public static Operator getOperator(String operatorName) throws EvaluatorExpressionException {

        OperatorNames operatorNames = OperatorNames.getOperator(operatorName);

        if (Objects.nonNull(operatorNames)) {

            switch(operatorNames) {

                case OR:
                    return new Or();

                case AND:
                    return new And();

                case NOT:
                    return new Not();

                case GREATER_THEN:
                    return new GreaterThen();

                case EQUALS:
                    return new Equals();

                case CONTAINS:
                    return new ContainsIn();

                case LESS_THEN:
                    return new LessThen();
            }

        } else {
            throw new EvaluatorExpressionException("Invalid Operator Name,"+operatorName);
        }
        return null;
    }
}
