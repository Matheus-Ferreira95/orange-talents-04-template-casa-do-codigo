package br.com.zupacademy.matheus.casadocodigo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsIdValidator.class)
@Target({ ElementType.FIELD }) //TYPE acho que é para anotação na classe, field em campos (atributos)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {
    String message() default "{br.com.zupacademy.matheus.casadocodigo.uniquevalue}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}
