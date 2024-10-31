package com.core.jpatraining2.validation;

import com.core.jpatraining2.interfaces.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateComparisonValidator implements ConstraintValidator<ValidDateRange, TaskDto> {

    @Override
    public boolean isValid(TaskDto taskDto, ConstraintValidatorContext context) {
        if(taskDto.getStartAt() != null || taskDto.getFinishAt() != null) {
            return false;
        }
        return taskDto.getFinishAt().isAfter(taskDto.getStartAt());
    }
}
