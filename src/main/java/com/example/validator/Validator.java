package com.example.validator;

public interface Validator<T> {
    boolean validate(T value);
}
