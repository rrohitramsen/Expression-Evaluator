package com.expression.evaluator.service;

import com.expression.evaluator.api.APIRequest;
import com.expression.evaluator.api.EvaluatorExpressionException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
@Service
public interface ExpressionEvaluatorService {

    /**
     * This method evaluate the expression.
     * @param apiRequest
     * @return
     */
    Map<Object, Boolean> evaluateExpression(APIRequest apiRequest) throws EvaluatorExpressionException;
}
