package com.expression.evaluator.api;

import com.expression.evaluator.FileUtils;
import com.expression.evaluator.service.ExpressionEvaluatorService;
import com.expression.evaluator.service.ExpressionEvaluatorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
@RunWith(SpringRunner.class)
public class APITest {

    @InjectMocks
    private API api;

    private MockMvc mockMvc;
    private ExpressionEvaluatorService expressionEvaluatorService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        expressionEvaluatorService = Mockito.mock(ExpressionEvaluatorServiceImpl.class);

        Field field = ReflectionUtils.findField(API.class, "expressionEvaluatorService");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, api, expressionEvaluatorService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(api).build();

    }

    @Test
    public void testEvaluateExpression() throws Exception {

        String apiRequestJson = FileUtils.readFileIntoJson(APIRequest.class, "api_request.json");
        Map<Object, Boolean> map = new HashMap<>();
        map.put("[OR, [IN, event.category, [infant, child, teen]], [LT, user.age, 18]]", true);
        map.put("[OR, [EQ, user.address.city, Los Angeles], [EQ, user.age, 35]]",true);

        String message = "Expression evaluated successfully.";
        APIResponse<Map<Object, Boolean>>  apiResponse = new APIResponse(message,  HttpStatus.OK.value(), map);
        ResponseEntity expectedResponse = new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
        Mockito.when(expressionEvaluatorService.evaluateExpression(any())).thenReturn(map);

        this.mockMvc.perform(post("/expression")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(apiRequestJson))
                .andExpect(status().is(200))
                .equals(expectedResponse);
    }

}


