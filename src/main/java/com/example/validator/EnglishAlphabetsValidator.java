package com.example.validator;

import org.springframework.stereotype.Component;

@Component
public class EnglishAlphabetsValidator implements Validator<String> {

    @Override
    public boolean validate(String value) {
        // Implement validation logic for English alphabets
        return value.matches("[a-zA-Z]+");
    }
}

