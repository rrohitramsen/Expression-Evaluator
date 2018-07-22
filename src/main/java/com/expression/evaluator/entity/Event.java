package com.expression.evaluator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class Event {

    @JsonProperty("category")
    private String category;

    public Event() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Event{" +
                "category='" + category + '\'' +
                '}';
    }
}
