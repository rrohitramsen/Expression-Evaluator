package com.expression.evaluator.api;

import com.expression.evaluator.service.ExpressionEvaluatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
@RestController
@RequestMapping("/expression")
@Api(value="Expression Evaluator API", description="Expression Evaluator API")
public class API {

    private static final Logger LOGGER = Logger.getLogger(API.class);

    @Autowired
    private ExpressionEvaluatorService expressionEvaluatorService;

    @ApiOperation(value = "Evaluate Expression.", response = APIResponse.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<APIResponse> evaluateExpression(@RequestBody APIRequest apiRequest) throws EvaluatorExpressionException {

        LOGGER.debug("Inside evaluateExpression with param "+apiRequest);
        Map<Object, Boolean> response  = expressionEvaluatorService.evaluateExpression(apiRequest);
        String message = "Expression evaluated successfully.";
        APIResponse<Map<Object, Boolean>>  apiResponse = new APIResponse(message,  HttpStatus.OK.value(), response);
        LOGGER.debug(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
