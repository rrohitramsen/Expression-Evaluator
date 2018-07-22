package com.expression.evaluator.service;

import com.expression.evaluator.api.APIRequest;
import com.expression.evaluator.api.EvaluatorExpressionException;
import com.expression.evaluator.engine.ExpressionEvaluatorEngine;
import com.expression.evaluator.entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
@Service
public class ExpressionEvaluatorServiceImpl implements ExpressionEvaluatorService {

    private static final Logger LOGGER = Logger.getLogger(ExpressionEvaluatorServiceImpl.class);

    @Override
    public Map<Object, Boolean> evaluateExpression(APIRequest apiRequest) throws EvaluatorExpressionException {

        User user = apiRequest.getUser();
        ArrayList<Object>[] expressions = apiRequest.getExpression();
        Map<Object, Boolean> resultMap = new HashMap<>();

        for (ArrayList<Object> expression : expressions) {
            LOGGER.debug("Evaluating expression :: "+expression.toString());

            if (!ExpressionEvaluatorEngine.isExpression(expression)) {
                throw new EvaluatorExpressionException("Invalid Expression,"+expression.toString());
            }

            boolean result = ExpressionEvaluatorEngine.evaluateExpression(user, expression);
            resultMap.put(expression, result);
        }
        LOGGER.debug("Result :: "+resultMap);
        return resultMap;
    }

}
