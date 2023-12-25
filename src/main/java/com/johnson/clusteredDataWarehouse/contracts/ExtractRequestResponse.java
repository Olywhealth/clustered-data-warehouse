package com.johnson.clusteredDataWarehouse.contracts;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Johnson on 24/12/2023
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtractRequestResponse {

    @AliasFor("responseHeader")
    String[] value() default {};

    @AliasFor("value")
    String[] responseHeader() default {};

    String[] requestHeader() default {};

    String[] requestParam() default {};

    String[] requestBody() default {};

    String[] responseBody() default {};

    String[] pathVariable() default {};

    String[] modelAttribute() default {};

    String[] sessionAttribute() default {};

    String[] cookieValue() default {};

    String[] matrixVariable() default {};

    String[] uriComponents() default {};

    String[] request() default {};

    String[] response() default {};

}
