package com.kgc.nl.kalah.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

/**
 * Validation constraint for a UUID in string format
 * 
 * @author Randhir Kumar
 *
 */
@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = {})
@Documented
public @interface UUID {
  String message() default "UUID has wrong format";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default {};
}
