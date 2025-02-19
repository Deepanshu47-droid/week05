package com.objecttojsonconverter;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        // Creating Car object
        Car car = new Car("Toyota", "Camry", 2023);

        // Converting to JSON
        String json = JavaObjectToJson.convertCarToJson(car);

        // Printing JSON
        System.out.println(json);
    }
}
