package org.example.titanic.parser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface BindColumn {

    int position() default -1;

    String column() default "";

    Class<? extends ConverterHandler> converter() default ConverterHandler.class;
}
