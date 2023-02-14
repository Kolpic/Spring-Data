package com.softuni.workshop.utils;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtilImpl implements ValidationUtil {

     private final Validator validator;

     @Autowired
     public ValidationUtilImpl() {
          validator = Validation
                  .buildDefaultValidatorFactory()
                  .getValidator();
     }

     public <E> boolean isValid(E entity) {
          return validator.validate(entity).isEmpty();
     }
}
