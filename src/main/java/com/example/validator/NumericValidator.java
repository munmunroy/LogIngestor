package com.example.validator;

import org.springframework.stereotype.Component;

@Component
public class NumericValidator implements Validator<String> {

    @Override
    public boolean validate(String value) {
        // Implement validation logic for numeric values
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

