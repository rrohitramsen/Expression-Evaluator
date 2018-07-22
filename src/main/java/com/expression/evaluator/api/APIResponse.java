package com.expression.evaluator.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class APIResponse<T> {

    @ApiModelProperty(notes = "API Response message.")
    @JsonProperty("message")
    private final String message;

    public APIResponse(String message, int responseCode, T body) {
        this.message = message;
        this.responseCode = responseCode;
        this.body = body;
    }

    @ApiModelProperty(notes = "API Response return code.")
    @JsonProperty("response_code")
    private final int responseCode;

    @ApiModelProperty(notes = "Expression Evaluation Result")
    @JsonProperty("result_body")
    private final T body;


    public String getMessage() {
        return message;
    }

    public int getReturnCode() {
        return responseCode;
    }

    public T getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "message='" + message + '\'' +
                ", responseCode=" + responseCode +
                ", body=" + body +
                '}';
    }
}
