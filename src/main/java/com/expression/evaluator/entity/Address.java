package com.expression.evaluator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class Address {

    @JsonProperty("city")
    private String city;

    @JsonProperty("address-line")
    private String addressLine;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zipcode")
    private String zipCode;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
