package com.expression.evaluator.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EvaluatorExpressionException extends Exception {
    
    public EvaluatorExpressionException(String message) {
        super(message);
    }
}
