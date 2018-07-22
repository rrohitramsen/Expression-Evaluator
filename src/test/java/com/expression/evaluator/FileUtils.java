package com.expression.evaluator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class FileUtils {

    private static final String BASE_PATH = "src/test/resources/";

    /**
     * Convert input file into json of given class type.
     * @param type
     * @param fileName
     * @param <T> class type
     * @return JSON representation of the object data from file.
     * @throws IOException
     */
    public static <T> String readFileIntoJson(Class<T> type, String fileName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(objectMapper.readValue(new File(BASE_PATH + fileName),  type));

    }


    /**
     * Convert Json to Object of given class type.
     * @param type
     * @param fileName
     * @param <T> class type
     * @return object of type T
     * @throws IOException
     */
    public static <T> T readObjectFromJsonFile(Class<T> type, String fileName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(BASE_PATH + fileName), type);

    }

}
