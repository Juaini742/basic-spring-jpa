package com.core.jpatraining2.exception;

public class DuplicateAttributeException extends RuntimeException {

    public DuplicateAttributeException(String attribute) {
        super(attribute + " already exists");
    }
}
