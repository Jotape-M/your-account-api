package com.jotapem.youraccount.validations.annotations.constraints;


import com.jotapem.youraccount.validations.annotations.OfAgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = OfAgeValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OfAgeConstraint {

    String message() default "The account cannot be owned by a minor.";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
