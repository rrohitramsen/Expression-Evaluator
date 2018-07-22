package com.expression.evaluator.api;

import com.expression.evaluator.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class APIRequest {

    @ApiModelProperty(notes = "API Request Entity")
    @JsonProperty("user")
    private User user;

    @ApiModelProperty(notes = "API Request Expression in Json Array format.")
    @JsonProperty("expression")
    /**
     * Format of expression
     *  [ OPERATOR, OPERAND, COMPARISON_VALUE(S) ]
     *  ["AND", ["EQ", "user.address.city", "Los Angeles"], ["GT", "user.age", 35]]
     */
    private ArrayList<Object> [] expression;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Object>[] getExpression() {
        return expression;
    }

    public void setExpression(ArrayList<Object>[] expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "APIRequest{" +
                "user=" + user +
                ", expression=" + Arrays.toString(expression) +
                '}';
    }
}
