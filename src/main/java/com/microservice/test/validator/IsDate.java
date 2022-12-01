package com.microservice.test.validator;

import com.microservice.test.constant.GenericConstant;
import org.hibernate.mapping.List;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = DateValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE, PARAMETER })
@Repeatable(IsDate.List.class)
@Retention(RUNTIME)
@Documented
public @interface IsDate {
    String message();
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
    String value ();

    @Target({ TYPE, FIELD, ANNOTATION_TYPE, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        IsDate[] value();
    }
}